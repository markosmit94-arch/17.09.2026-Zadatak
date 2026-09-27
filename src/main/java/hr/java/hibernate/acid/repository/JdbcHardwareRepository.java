package hr.java.hibernate.acid.repository;

import hr.java.hibernate.acid.domain.Hardware;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
@AllArgsConstructor
public class JdbcHardwareRepository implements HardwareRepository {

    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Hardware> getAllHardware() {
        return jdbcTemplate.query("SELECT * FROM HARDWARE", new HardwareMapper());
    }

    @Override
    public List<Hardware> getHardwareBySifra(String sifra) {
        return jdbcTemplate.query("SELECT * FROM HARDWARE WHERE sifra = ?", new HardwareMapper(), sifra);
    }

    @Override
    public Integer saveNewHardware(Hardware hardware){
        final String SQL = "SELECT ID FROM FINAL TABLE (INSERT INTO HARDWARE (naziv, sifra, cijena, tip, kolicina) VALUES (?, ?, ?, ?, ?)) HARDWARE";
        Integer generatedId = jdbcTemplate.queryForObject(SQL, Integer.class, hardware.getNaziv(), hardware.getSifra(), hardware.getCijena(), hardware.getTip(), hardware.getKolicina());
        hardware.setId(generatedId);
        return hardware.getId();
    }

    @Override
    public Optional<Hardware> updateHardware(Hardware hardwareToUpdate, Integer id) {
        if(hardwareByIdExists(id)){
            final String SQL = "UPDATE HARDWARE SET naziv = ?, sifra = ?, cijena = ?, tip = ?, kolicina = ? WHERE id = ?";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL);
                ps.setString(1, hardwareToUpdate.getNaziv());
                ps.setString(2, hardwareToUpdate.getSifra());
                ps.setDouble(3, hardwareToUpdate.getCijena());
                ps.setString(4, hardwareToUpdate.getTip().getName());
                ps.setInt(5, hardwareToUpdate.getKolicina());
                ps.setInt(6, id);
                return ps;
            });
            hardwareToUpdate.setId(id);
            return Optional.of(hardwareToUpdate);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public boolean hardwareByIdExists(Integer id) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT (*) FROM HARDWARE WHERE ID = ?", Integer.class, id);
        return count > 0;
    }

    @Override
    public boolean deleteHardwareById(Integer id) {
        if(hardwareByIdExists(id)){
            jdbcTemplate.update("DELETE FROM HARDWARE WHERE id = ?", id);
            return true;
        }
        else {
            return false;
        }
    }

    private static class HardwareMapper implements RowMapper<Hardware> {

        public Hardware mapRow(ResultSet rs, int i) throws SQLException {

            Hardware newHardware = new Hardware();
            newHardware.setId(rs.getInt("ID"));
            newHardware.setNaziv(rs.getString("NAZIV"));
            newHardware.setSifra(rs.getString("SIFRA"));
            newHardware.setCijena(rs.getDouble("CIJENA"));
            Integer tipId = rs.getInt("TIPID");
            newHardware.setKolicina(rs.getInt("KOLICINA"));
            return newHardware;
        }
    }
}
