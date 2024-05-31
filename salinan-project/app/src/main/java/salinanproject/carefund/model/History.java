package salinanproject.carefund.model;

public class History {

    private double nominal;
    private String yayasan;
    private String metode;

    public History(String yayasan, double nominal, String metode) {
        this.yayasan = yayasan;
        this.nominal = nominal;
        this.metode = metode;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public double getNominal() {
        return nominal;
    }

    public void setYayasan(String yayasan) {
        this.yayasan = yayasan;
    }

    public String getYayasan() {
        return yayasan;
    }

    public void setMetode(String metode) {
        this.metode = metode;
    }

    public String getMetode() {
        return metode;
    }
}
