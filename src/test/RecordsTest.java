import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.*;
import java.nio.file.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecordsTest{
    @org.junit.Test
    public void testrecordwriter() throws IOException {
        List<BigDecimal> location = new ArrayList<>();
        List<BigDecimal> tempSig = new ArrayList<>();
        List<BigDecimal> bpSig = new ArrayList<>();
        List<BigDecimal> hrSig = new ArrayList<>();
        List<BigDecimal> rrSig = new ArrayList<>();
        List<BigDecimal> ecgSig = new ArrayList<>();
        Patient pat = new Patient(12345,"Nicolas","Calvo Peiro",19,"A+",location,ecgSig,bpSig,hrSig,rrSig,tempSig);
        Records testrecrd = new Records();
        testrecrd.recordwriter(pat);
        boolean flag1 = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String file_path = System.getProperty("user.home") + System.getProperty("file.separator") + "RPM_DATA" + System.getProperty("file.separator") + "PAST_RECORDS" + System.getProperty("file.separator") + pat.getLastname() + "_" + pat.getFirstname() + "_" + pat.getPatID() + System.getProperty("file.separator") + dtf.format(now) + ".csv";
        Path path = Paths.get(file_path);
        if (Files.exists(path)) flag1=true;
        assertEquals(true,flag1);
    }
    }