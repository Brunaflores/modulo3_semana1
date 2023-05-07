// Importando as classes necessárias
package br.com.joaquina.m3s01projetorevisao.controllers;
import br.com.joaquina.m3s01projetorevisao.entities.Despesa;
import br.com.joaquina.m3s01projetorevisao.services.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// Anotação que indica que a classe é um controlador REST do Spring
@RestController
// Mapeando a rota base para o controlador
@RequestMapping("/despesas")
public class DespesaController {

    private final DespesaService despesaService;

    // Injetando o serviço que lida com a lógica de negócio das despesas
    @Autowired
    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }


    // Mapeando o método HTTP POST para criar uma nova despesa
    //Exemplo de requisição: POST http://localhost:8080/despesas?credor=João&dataVencimento=2021-10-10&valor=1000&descricao=Aluguel
    @PostMapping
    public ResponseEntity<Despesa> criarDespesa(
            // Recebendo o parâmetro 'credor' da requisição
            @RequestParam String credor,
            // Recebendo o parâmetro 'dataVencimento' da requisição
            @RequestParam LocalDate dataVencimento,
            // Recebendo o parâmetro 'valor' da requisição
            @RequestParam BigDecimal valor,
            // Recebendo o parâmetro 'descricao' da requisição (opcional)
            @RequestParam(required = false) String descricao) {
        // Chamando o serviço para criar a despesa com os parâmetros recebidos
        Despesa despesa = despesaService.criarDespesa(credor, dataVencimento, valor, descricao);
        // Retornando a despesa criada com o código de status HTTP 201 (Created)
        return new ResponseEntity<>(despesa, HttpStatus.CREATED);
    }

    // Mapeando o método  para atualizar uma despesa
    @PutMapping ("/{id}")
    // Recebendo o parâmetro 'id' da requisição
    //Para usar no PostaMan http://localhost:8080/despesas/1
    //enviar no body o json com os dados a serem atualizados credor, dataVencimento, valor, descricao

    public ResponseEntity<Despesa> atualizarDespesa(
            // Recebendo o parâmetro 'id' da requisição
            @PathVariable Long id,
            // Recebendo o parâmetro 'credor' da requisição
            @RequestParam String credor,
            // Recebendo o parâmetro 'dataVencimento' da requisição
            @RequestParam LocalDate dataVencimento,
            // Recebendo o parâmetro 'valor' da requisição
            @RequestParam BigDecimal valor,
            // Recebendo o parâmetro 'descricao' da requisição (opcional)
            @RequestParam(required = false) String descricao) {
        // Chamando o serviço para atualizar a despesa com os parâmetros recebidos
        Despesa despesa = despesaService.atualizarDespesa(id, credor, dataVencimento, valor, descricao);
        // Retornando a despesa atualizada com o código de status HTTP 200 (OK)
        return new ResponseEntity<>(despesa, HttpStatus.OK);
    }

    @GetMapping
    // Mapeando o método GET para buscar todas as despesas
    //Para usar no PostaMan http://localhost:8080/despesas
    public ResponseEntity<List<Despesa>> buscarTodasDespesas() {
        // Chamando o serviço para buscar todas as despesas
        List<Despesa> despesas = despesaService.buscarTodasDespesas();
        // Retornando a lista de despesas com o código de status HTTP 200 (OK)
        return new ResponseEntity<>(despesas, HttpStatus.OK);
    }

    // Mapeando o método GET para buscar uma despesa por status
    //EX: Para usar no PostaMan http://localhost:8080/despesas/status?status=PENDENTE
    @GetMapping("/status")
    // Recebendo o parâmetro 'status' da requisição
    public ResponseEntity<List<Despesa>> buscarDespesasPorStatus(@RequestParam String status) {
        // Chamando o serviço para buscar as despesas por status
        List<Despesa> despesas = despesaService.buscarDespesasPorStatus(status);
        // Retornando a lista de despesas com o código de status HTTP 200 (OK)
        return new ResponseEntity<>(despesas, HttpStatus.OK);
    }

    //Metodo put para pagar uma despesa
    @PutMapping("/{id}/pagar")
    // EX: Para usar no PostaMan http://localhost:8080/despesas/1/pagar
    // Recebendo o parâmetro 'id' da requisição
    public ResponseEntity<Despesa> pagarDespesa(@PathVariable Long id) {
        // Chamando o serviço para pagar a despesa com o id recebido
        Despesa despesa = despesaService.pagarDespesa(id);
        // Retornando a despesa paga com o código de status HTTP 200 (OK)
        return new ResponseEntity<>(despesa, HttpStatus.OK);
    }

    //Metodo put para estornar uma despesa
    @PutMapping("/{id}/estornar")
    // EX: Para usar no PostaMan http://localhost:8080/despesas/1/estornar
    // Recebendo o parâmetro 'id' da requisição
    public ResponseEntity<Despesa> estornarDespesa(@PathVariable Long id) {
        // Chamando o serviço para estornar a despesa com o id recebido
        Despesa despesa = despesaService.estornarDespesa(id);
        // Retornando a despesa estornada com o código de status HTTP 200 (OK)
        return new ResponseEntity<>(despesa, HttpStatus.OK);
    }

}




