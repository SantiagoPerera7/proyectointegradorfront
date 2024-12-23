/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliariafront.dto;

/**
 *
 * @author santi
 */
public class AlquilerDTO{
    private Integer id;
    private Boolean exoneraIRPF;
    private PropiedadDTO propiedad;
    private ContratoDTO contrato;
    private Double importe;
    private GarantiaDTO tipoGarantia;
    private InquilinoDTO inquilino;
    private ClienteDTO propietario;
    public AlquilerDTO(){
        
    }
    public AlquilerDTO(Boolean exoneraIRPF,PropiedadDTO propiedad, ContratoDTO contrato, Double importe, GarantiaDTO tipoGarantia,InquilinoDTO inquilino, ClienteDTO propietario){
        this.exoneraIRPF=exoneraIRPF;
        this.propiedad=propiedad;
        this.contrato=contrato;
        this.importe=importe;
        this.tipoGarantia=tipoGarantia;
        this.inquilino=inquilino;
        this.propietario=propietario;
    }
        public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public ClienteDTO getPropietario() {
       return propietario;
    }

    public void setPropietario(ClienteDTO propietario) {
        this.propietario = propietario;
    }
    public InquilinoDTO getInquilino() {
        return inquilino;
    }

    public void setInquilino(InquilinoDTO inquilino) {
        this.inquilino = inquilino;
    }
    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }
    public Boolean getExoneraIRPF() {
        return exoneraIRPF;
    }

    public void setExoneraIRPF(Boolean exoneraIRPF) {
        this.exoneraIRPF = exoneraIRPF;
    }

    public PropiedadDTO getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadDTO propiedad) {
        this.propiedad = propiedad;
    }

    public ContratoDTO getContrato() {
        return contrato;
    }

    public void setContrato(ContratoDTO contrato) {
        this.contrato = contrato;
    }
    public GarantiaDTO getTipoGarantia() {
        return tipoGarantia;
    }

    public void setTipoGarantia(GarantiaDTO tipoGarantia) {
        this.tipoGarantia = tipoGarantia;
    }
    @Override
    public String toString(){
        return "Dirección: "+this.getPropiedad().getDireccion()+" - Inquilino: "+this.getInquilino().getNombre()+" "+this.getInquilino().getApellido();
    }
    public Integer calcularIRPF(){
        return 100;
    }
    public Integer calcularIVA(){
        return 200;
    }
    public Integer calcularComision(){
        return 300;
    }
    public Integer calcularGastoTotalMensual(){
        return this.getImporte().intValue()+this.calcularIRPF()+this.calcularIVA()+this.calcularComision();
    }
}

