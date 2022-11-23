package j2b.nft_generator.test;

import j2b.nft_generator.imageconverter.constant.Effect;
import j2b.nft_generator.member.dto.AddMemberReqDTO;
import j2b.nft_generator.member.entity.Member;
import j2b.nft_generator.member.repository.MemberRepository;
import j2b.nft_generator.nft.dto.AddNftReqDTO;
import j2b.nft_generator.nft.repository.NftRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import static j2b.nft_generator.member.entity.Member.createMember;
import static j2b.nft_generator.nft.entity.Nft.createNft;

/**
 * 테스트 데이터 삽입을 담당하는 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TestData {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }

    @Component
    @RequiredArgsConstructor
    static class InitService {

        private final MemberRepository memberRepository;
        private final NftRepository nftRepository;

        @Transactional
        public void init() {
            // 1. Member 삽입
            Member member1 = memberRepository.save(createMember(new AddMemberReqDTO("조민호", "abcde", "pass1234")));
            Member member2 = memberRepository.save(createMember(new AddMemberReqDTO("조은남", "abbbb", "pass1234")));
            Member member3 = memberRepository.save(createMember(new AddMemberReqDTO("백경석", "acccc", "pass1234")));

            // 2. NFT 삽입
            nftRepository.save(createNft(new AddNftReqDTO("NFT1", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            100000, 0.2, "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            Effect.CARTOON, 100, 0.5),
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/nft/test1_1668930530666.jpg",
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/preview/preview1_1668931325270.jpg",
                    "test1_1668930530666.jpg", "preview/preview1_1668931325270.jpg", member1));

            nftRepository.save(createNft(new AddNftReqDTO("NFT2", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            200000, 0.2, "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            Effect.CARTOON, 100, 0.5),
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/nft/test2_1668930547674.jpg",
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/preview/preview2_1668931337100.jpg",
                    "test2_1668930547674.jpg", "preview/preview2_1668931337100.jpg", member2));

            nftRepository.save(createNft(new AddNftReqDTO("NFT3", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            150000, 0.2, "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            Effect.CARTOON, 100, 0.5),
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/nft/test3_1668930568313.jpg",
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/preview/preview3_1668931346553.jpg",
                    "test3_1668930568313.jpg", "preview/preview3_1668931346553.jpg", member3));

            nftRepository.save(createNft(new AddNftReqDTO("NFT4", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            300000, 0.2, "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            Effect.CARTOON, 100, 0.5),
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/nft/test4_1668930579370.jpg",
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/preview/preview4_1668931354488.jpg",
                    "test4_1668930579370.jpg", "preview/preview4_1668931354488.jpg", member1));

            nftRepository.save(createNft(new AddNftReqDTO("NFT5", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            400000, 0.2, "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            Effect.CARTOON, 100, 0.5),
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/nft/test5_1668930587678.jpg",
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/preview/preview5_1668931362179.jpg",
                    "test5_1668930587678.jpg", "preview/preview5_1668931362179.jpg", member2));

            nftRepository.save(createNft(new AddNftReqDTO("NFT6", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            500000, 0.2, "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            Effect.CARTOON, 100, 0.5),
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/nft/test6_1668930595763.jpg",
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/preview/preview6_1668931369652.jpg",
                    "test6_1668930595763.jpg", "preview/preview6_1668931369652.jpg", member3));

            nftRepository.save(createNft(new AddNftReqDTO("NFT7", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            1000000, 0.2, "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            Effect.CARTOON, 100, 0.5),
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/nft/test7_1668930603515.jpg",
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/preview/preview7_1668931388178.jpg",
                    "test7_1668930603515.jpg", "preview/preview7_1668931388178.jpg", member1));

            nftRepository.save(createNft(new AddNftReqDTO("NFT8", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            2000000, 0.2, "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?",
                            Effect.CARTOON, 100,  0.5),
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/nft/test8_1668930611849.jpg",
                    "https://j2b-s3-bucket.s3.ap-northeast-2.amazonaws.com/preview/preview8_1668931397264.jpg",
                    "test8_1668930611849.jpg", "preview/preview8_1668931397264.jpg", member1));
        }
    }
}
