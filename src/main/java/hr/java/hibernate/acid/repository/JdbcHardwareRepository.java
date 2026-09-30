package hr.java.hibernate.acid.repository;

import hr.java.hibernate.acid.domain.Hardware;
import hr.java.hibernate.acid.domain.Type;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
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
    public Integer saveNewHardware(Hardware hardware) {
        final String SQL = "INSERT INTO HARDWARE (naziv, sifra, cijena, tipId, kolicina) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, hardware.getNaziv());
            ps.setString(2, hardware.getSifra());
            ps.setDouble(3, hardware.getCijena());
            ps.setInt(4, hardware.getType().getId());
            ps.setInt(5, hardware.getKolicina());
            return ps;
        }, keyHolder);
        Integer generatedId = Objects.requireNonNull(keyHolder.getKey()).intValue();
        hardware.setId(generatedId);
        return generatedId;
    }

    @Override
    public Optional<Hardware> updateHardware(Hardware hardwareToUpdate, Integer id) {
        if (hardwareByIdExists(id)) {
            final String SQL = "UPDATE HARDWARE SET naziv = ?, sifra = ?, cijena = ?, tipId = ?, kolicina = ? WHERE id = ?";
            jdbcTemplate.update(SQL,
                    hardwareToUpdate.getNaziv(),
                    hardwareToUpdate.getSifra(),
                    hardwareToUpdate.getCijena(),
                    hardwareToUpdate.getType().getId(),
                    hardwareToUpdate.getKolicina(),
                    id);
            hardwareToUpdate.setId(id);
            return Optional.of(hardwareToUpdate);
        }
        return Optional.empty();
    }

    @Override
    public boolean hardwareByIdExists(Integer id) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM HARDWARE WHERE ID = ?", Integer.class, id);
        return count > 0;
    }

    @Override
    public boolean deleteHardwareById(Integer id) {
        if (hardwareByIdExists(id)) {
            jdbcTemplate.update("DELETE FROM HARDWARE WHERE id = ?", id);
            return true;
        }
        return false;
    }

    private static class HardwareMapper implements RowMapper<Hardware> {

        @Override
        public Hardware mapRow(ResultSet rs, int i) throws SQLException {
            Hardware newHardware = new Hardware();
            newHardware.setId(rs.getInt("ID"));
            newHardware.setNaziv(rs.getString("NAZIV"));
            newHardware.setSifra(rs.getString("SIFRA"));
            newHardware.setCijena(rs.getDouble("CIJENA"));
            newHardware.setType(Type.fromId(rs.getInt("TIPID")));
            newHardware.setKolicina(rs.getInt("KOLICINA"));
            return newHardware;
        }
    }
}
