package carefund.project.model;

public class History {
    
    private double nominal;
    private String tujuan;
    private String tanggal;
    private String waktu;

    public History(double nominal, String tujuan, String tanggal, String waktu) {
        this.nominal = nominal;
        this.tujuan = tujuan;
        this.tanggal = tanggal;
        this.waktu = waktu;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public double getNominal() {
        return nominal;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getWaktu() {
        return waktu;
    }
}
