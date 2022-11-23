package j2b.nft_generator.imageconverter.service;

import j2b.nft_generator.file.FileUploadUtil;
import j2b.nft_generator.file.dto.FileUploadToServerReqDTO;
import j2b.nft_generator.imageconverter.constant.Effect;
import j2b.nft_generator.imageconverter.dto.ConvertImageReqDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.UUID.randomUUID;

/**
 * 쉘 스크립트를 이용하여 업로드한 이미지를 변환하는 클래스입니다.
 * @version 1.0.3
 * @author CHO Min HO
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ImageConverter {

    private final FileUploadUtil fileUploadUtil;


    /**
     * 이미지 변환기 파이썬 파일 로컬 경로
     */
    private final String IMAGE_CONVERTER_LOCAL_PATH = File.separator + "home" + File.separator + "ec2-user" +
            File.separator + "nft-generator.py";
    /**
     * 변환된 이미지 저장 로컬 경로
     */
    private final String CONVERTED_IMAGE_LOCAL_PATH = File.separator + "home" + File.separator + "ec2-user" +
            File.separator + "convertedFile" + File.separator;
    /**
     * 추출된 JSONd 저장 로컬 경로
     */
    private final String EXTRACTED_JSON_LOCAL_PATH = File.separator + "home" + File.separator + "ec2-user" +
            File.separator + "json" + File.separator;
    /**
     * 파이썬 실행 명령어
     */
    private final String EXECUTE_PYTHON_COMMAND = "sudo python3";
    /**
     * JSON 확장자
     */
    private final String JSON_EXTENSION = ".json";

    /**
     * S3에 업로드되어 있는 이미지를 서버로 다운받아 이미지 변환을 진행하고, 다시 S3에 이미지를 업로드합니다.
     * token ID는 UUID를 통해 발급합니다.
     * @param dto 원본 이미지 URL, 변환할 이미지 정보
     * @return 변환 후 이미지의 로컬 경로
     */
    public String convertImage(ConvertImageReqDTO dto, FileUploadToServerReqDTO toServerReqDTO) {

        // TODO : JSON 만들어지고 나서 이미지 URL (우리 페이지에서의 상품 URL), NFT 설명에 대해 JSON 수정

        if (dto.getEffect() == Effect.CARTOON || dto.getEffect() == Effect.DETAIL || dto.getEffect() == Effect.SKETCH) {
            // 이미지 변환
            executeCommand(EXECUTE_PYTHON_COMMAND + " " + IMAGE_CONVERTER_LOCAL_PATH + " " +
                    CONVERTED_IMAGE_LOCAL_PATH + " " + dto.getEffect().getValue() + " " + toServerReqDTO.getFilePath() +
                    " " + dto.getSigmaS() + " " + dto.getSigmaR() + " " + toServerReqDTO.getFileName());

            // 변환된 이미지의 경로 반환
            return CONVERTED_IMAGE_LOCAL_PATH + toServerReqDTO.getFileName();
        }

        // TODO : Sketch 를 사용자에게 입력받는 로직
        /*else if (dto.getEffect() == Effect.SKETCH) {
            // 이미지 변환
            executeCommand("sudo python3 " + IMAGE_CONVERTER +
                    IMAGE_LOCAL_PATH + " " + dto.getEffect().getValue() + " " + toServerReqDTO.getFilePath() +  " " +
                    dto.getSigmaS() + " " + dto.getSigmaR() + " " + "\"" + dto.getNftDescription() + "\" " +
                    nftPageLink + "  " +  "imageLink "  + randomUUID().toString().substring(0, 10) + " " +
                    toServerReqDTO.getFileName() + " " + dto.getSketch().getValue() + " " + dto.getShadeFactor());

            // 변환된 이미지의 경로 반환
            return IMAGE_LOCAL_PATH + toServerReqDTO.getFileName();
        }*/

        return null;
    }

    /**
     * 변환 후 서버에 저장된 이미지 정보와 어떻게 변환되었는지 정보를 가지고 JSON (NFT metadata)를 서버에서 생성합니다.
     * @param dto 원본 이미지 URL, 변환할 이미지 정보
     * @param toServerReqDTO 서버에 저장된 이미지 정보
     * @param nftItemPageUrl NFT 상품 페이지 URL
     * @param nftImageUrl NFT 이미지 URL
     * @return JSON 파일이 저장된 로컬 경로
     */
    public String
    extractJsonFromImage(ConvertImageReqDTO dto, FileUploadToServerReqDTO toServerReqDTO,
                                       String nftItemPageUrl, String nftImageUrl) {

        executeCommand(EXECUTE_PYTHON_COMMAND + " " + EXTRACTED_JSON_LOCAL_PATH + " " +
                toServerReqDTO.getOriginalFileName() + " " + "\"" + dto.getNftDescription() + "\" " +
                nftItemPageUrl + "  " + nftImageUrl + " " + randomUUID().toString().substring(0, 10) + " " +
                dto.getEffect().getValue() + " " + dto.getSigmaS() + " " + dto.getSigmaR() + "  " +
                toServerReqDTO.getFileName());

        // 추출된 JSON의 경로 반환
        return EXTRACTED_JSON_LOCAL_PATH + toServerReqDTO.getFileName() + JSON_EXTENSION;
    }

    /**
     * Bash 명령어를 실행하는 메서드입니다.
     * @param command 명령어
     */
    private void executeCommand(String command) {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
