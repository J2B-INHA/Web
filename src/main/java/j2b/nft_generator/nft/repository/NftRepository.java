package j2b.nft_generator.nft.repository;

import j2b.nft_generator.nft.entity.Nft;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * JPA NFT repository 인터페이스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
public interface NftRepository extends JpaRepository<Nft, Long> {
    Optional<Nft> findById(Long id);
    List<Nft> findByName(String name);
}
