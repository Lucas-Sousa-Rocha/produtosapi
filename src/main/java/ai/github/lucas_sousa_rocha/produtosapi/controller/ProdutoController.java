package ai.github.lucas_sousa_rocha.produtosapi.controller;

import ai.github.lucas_sousa_rocha.produtosapi.model.Produto;
import ai.github.lucas_sousa_rocha.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {


    // INJEÇÃO DE DEPÊNCIA
    private final ProdutoRepository produtoRepository;

    // CONSTRUTOR PARA REALIZAR O ACESSO AO REPOSITORY
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // CADASTRA O PRODUTO
    @PostMapping("cadastro-produto")
    public String salvarProduto(@RequestBody Produto produto) {
    var id = UUID.randomUUID().toString();
    produto.setId(id);
    produtoRepository.save(produto);
    System.out.println("Produto recebido: " + produto);
    return "Produto cadastrado com sucesso! " + produto.getNome();
    }

    // LISTA TODOS OS PRODUTOS
    @GetMapping("/listar-produto")
    public List<Produto> listarTodosProdutos() {
    return produtoRepository.findAll();
    }

    // REALIZA A BUSCA POR ID DO PRODUTO
    @GetMapping(path="listar-porid/{id}")
    public Optional<Produto> buscarProdutosPorId(@PathVariable String id) {
    return produtoRepository.findById(id);
    }

    // REALIZA A BUSCA POR NOME POR MEIO DO SELECT LIKE
    @GetMapping(path="listar-pornome/{nome}")
    public List<Produto> buscarProdutosPorNome(@PathVariable("nome") String nome) {
    return produtoRepository.bucarPorNome(nome);
    }

    // EXCLUI O PRODUTO COM BASE NO ID
    @DeleteMapping(path = "excluir-produto/{id}")
    public String excluirProduto(@PathVariable String id) {
        produtoRepository.deleteById(id);
        return "Produto removido com sucesso!";
    }

    // REALIZA A EDIÇÃO DO PRODUTO JÁ SALVO
    @PutMapping("editar-produto/{id}")
    public String editarProduto(@PathVariable String id,@RequestBody Produto produto) {
    produto.setId(id);
    produtoRepository.save(produto);
    return "Produto atualizado com sucesso!";
    }

}
