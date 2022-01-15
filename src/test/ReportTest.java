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

public class ReportTest{
    @org.junit.Test
    public void testrecordwriter() throws IOException {
        List<BigDecimal> location = new ArrayList<>();
        List<BigDecimal> tempSig = new ArrayList<>();
        List<BigDecimal> bpSig = new ArrayList<>();
        List<BigDecimal> hrSig = new ArrayList<>();
        List<BigDecimal> rrSig = new ArrayList<>();
        List<BigDecimal> ecgSig = new ArrayList<>();
        Patient pat = new Patient(12345,"Alvin","Chen",20,"A+",location,ecgSig,bpSig,hrSig,rrSig,tempSig);
        generate_report(pat);
        boolean flag1 = false;
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String current_time = sdf.format(date);
        String file_path = System.getProperty("user.home") + System.getProperty("file.separator") + "RPM_DATA" + System.getProperty("file.separator") + "Reports" + System.getProperty("file.separator") + pat.getLastname() + "_" + pat.getFirstname() + "_" + pat.getPatID() + System.getProperty("file.separator") + current_pat.getLastname() + "_" + current_pat.getFirstname() + "_" + current_pat.getPatID() + "_" + current_time + ".txt";
        Path path = Paths.get(file_path);
        if (Files.exists(path)) flag1=true;
        assertEquals(true,flag1);
    }
    }