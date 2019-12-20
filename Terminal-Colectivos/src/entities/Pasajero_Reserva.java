package entities;

public class Pasajero_Reserva implements Comparable<Pasajero_Reserva> {
	
	private Pasajero pasajero;
	private Reserva reserva;
	private int asiento;

	
	public Pasajero getPasajero() {
		return pasajero;
	}
	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public int getAsiento() {
		return asiento;
	}
	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}
	
	@Override
    public int compareTo(Pasajero_Reserva pas_res) 
	{
	    String a=new String(String.valueOf(this.asiento));
        String b=new String(String.valueOf(pas_res.getAsiento()));
        return a.compareTo(b);
    }

		
	

}
