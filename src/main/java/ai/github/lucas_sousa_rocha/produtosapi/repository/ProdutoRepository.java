package ai.github.lucas_sousa_rocha.produtosapi.repository;

import ai.github.lucas_sousa_rocha.produtosapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;



public interface ProdutoRepository extends JpaRepository<Produto, String> {

    @Query("SELECT p FROM Produto p WHERE p.nome LIKE %:nome%")
    List<Produto> buscarPorNome(@Param("nome")String nome);

}
