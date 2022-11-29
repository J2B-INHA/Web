package j2b.nft_generator.bash;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Bash 커맨드를 생성하는 서비스 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Service
@Slf4j
public class BashService {

    /**
     * Bash 명령어를 실행하는 메서드입니다.
     * @param command 명령어
     */
    public void executeCommand(List<String> command) {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);
        try {
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                log.info(line);
            }

            process.getErrorStream().close();
            process.getInputStream().close();
            process.getOutputStream().close();

            int exitCode = process.waitFor();
            log.info("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * bash 명령어를 만드는 메서드입니다.
     * @param command 명령어 인자
     * @return 생성된 bash 명령어
     */
    public String makeCommand(List<String> command) {
        StringBuilder commandStr = new StringBuilder();
        for (String c : command) {
            commandStr.append(c).append(" ");
        }
        return commandStr.toString();
    }
}
