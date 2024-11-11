import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Cita {
    private Paciente paciente;
    private LocalDateTime fechaHora;

    public Cita(Paciente paciente, LocalDateTime fechaHora) {
        this.paciente = paciente;
        this.fechaHora = fechaHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFecha() {
        return fechaHora.toLocalDate();
    }

    public LocalTime getHora() {
        return fechaHora.toLocalTime();
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return "Cita:" +
                "paciente = " + paciente +
                ", fechaHora = " + fechaHora;
    }
}