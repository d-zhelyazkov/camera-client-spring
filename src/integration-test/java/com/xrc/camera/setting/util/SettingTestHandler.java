package com.xrc.camera.setting.util;

import com.xrc.camera.model.Setting;
import com.xrc.camera.setting.CameraSetting;
import com.xrc.util.Comparator;
import org.junit.Assert;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class SettingTestHandler<T> {

    private final Setting setting;

    private Logger log;

    CameraSetting<T> cameraSetting;

    private Function<T, Boolean> valueValidator =
            (T value) -> {
                List<T> supportedValues = cameraSetting.getValues();
                return supportedValues.contains(value);
            };

    private Comparator<T> valueComparator = new Comparator<>((T o1, T o2) ->
            (Objects.equals(o1, o2)) ? 0 : 1);

    private Supplier<Stream<T>> validValuesSupplier =
            () -> cameraSetting.getValues().stream();

    private Supplier<Stream<T>> invalidValuesSupplier = Stream::empty;


    public SettingTestHandler(Setting setting, CameraSetting<T> cameraSetting) {
        this.setting = setting;
        this.cameraSetting = cameraSetting;

        this.log = Logger.getLogger(String.format("%s(%s)", this.getClass().getSimpleName(), setting));

        log.info("Setting info: " + cameraSetting.getSettingInfo());
    }

    public void verifySettingType() {
        Setting actualSetting = cameraSetting.getSetting();
        log.info("Verifying setting type " + actualSetting);

        Assert.assertEquals(this.setting, actualSetting);
    }

    public void verifySettingValue() {
        T value = cameraSetting.getValue();
        log.info("Verifying value " + value);

        Assert.assertNotNull(value);
        Assert.assertTrue(valueValidator.apply(value));
    }

    public void verifySupportedValues() {
        List<T> supportedValues = cameraSetting.getValues();
        log.info("Verifying supported values " + supportedValues);

        Assert.assertNotNull(supportedValues);
        Assert.assertFalse(supportedValues.isEmpty());

        long distinctValues = supportedValues.stream()
                .distinct()
                .count();
        Assert.assertEquals(distinctValues, supportedValues.size());
    }

    public void testValidValues() {
        log.info("Testing valid values.");

        T initialValue = cameraSetting.getValue();
        try {
            testValues(validValuesSupplier, this::setValue);
        } finally {
            setValue(initialValue);
        }
    }

    public void testInvalidValues() {
        log.info("Testing invalid values.");

        testValues(invalidValuesSupplier, this::setSettingUnsupported);
    }

    public void setValue(T value) {
        boolean editable = cameraSetting.isEditable();
        if (editable) {
            cameraSetting.setValue(value);
            checkValue(value, cameraSetting.getValue());
        } else {
            setSettingUnsupported(value);
        }
    }

    public void setSettingUnsupported(T value) {
        T currentValue = cameraSetting.getValue();
        try {
            cameraSetting.setValue(value);
            Assert.fail("Successfully set unsupported setting value " + value);
        } catch (HttpClientErrorException.BadRequest e) {
            checkValue(currentValue, cameraSetting.getValue());
        }
    }

    public void setValueValidator(Function<T, Boolean> valueValidator) {
        this.valueValidator = valueValidator;
    }

    public void setValueComparator(java.util.Comparator<T> valueComparator) {
        this.valueComparator = new Comparator<>(valueComparator);
    }

    public void setValidValuesSupplier(Supplier<Stream<T>> validValuesSupplier) {
        this.validValuesSupplier = validValuesSupplier;
    }

    public void setInvalidValuesSupplier(Supplier<Stream<T>> invalidValuesSupplier) {
        this.invalidValuesSupplier = invalidValuesSupplier;
    }

    public CameraSetting<T> getCameraSetting() {
        return cameraSetting;
    }

    private void checkValue(T expected, T actual) {
        Assert.assertTrue(
                String.format(
                        "Value not correct. Expected %s, got %s.",
                        expected, actual),
                valueComparator.equal(expected, actual));

    }


    private void testValues(
            Supplier<Stream<T>> valueProducer,
            Consumer<T> setMethod) {

        Collection<AssertionError> errors = new ArrayList<>();
        valueProducer.get()
                .forEach((T value) -> {
                    try {
                        log.info("Testing value " + value);
                        setMethod.accept(value);
                    } catch (AssertionError e) {
                        errors.add(e);
                        log.severe(e.toString());
                    }
                });
        Assert.assertTrue(
                "Some values set went unexpected: " + errors,
                errors.isEmpty());
    }
}
