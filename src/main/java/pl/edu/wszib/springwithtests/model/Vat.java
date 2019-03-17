package pl.edu.wszib.springwithtests.model;

public enum Vat {
    VALUE_23(0.23d),
    VALUE_8(0.08d),
    VALUE_5(0.05d),
    VALUE_0(0d);

    public final double rate;

    Vat(double rate) {
        this.rate = rate;
    }
}
