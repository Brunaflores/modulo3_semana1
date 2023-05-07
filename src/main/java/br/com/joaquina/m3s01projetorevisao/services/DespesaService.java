// Importando as classes necessárias
package br.com.joaquina.m3s01projetorevisao.services;
import br.com.joaquina.m3s01projetorevisao.entities.Despesa;
import br.com.joaquina.m3s01projetorevisao.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// Anotação que indica que a classe é um serviço do Spring
@Service
public class DespesaService {

    // Injetando o repositório para acesso aos dados de Despesa
    @Autowired
    private DespesaRepository despesaRepository;

    // Método para criar uma nova despesa
    public Despesa criarDespesa(String credor, LocalDate dataVencimento, BigDecimal valor, String descricao) {
        // Criando uma nova instância de Despesa
        Despesa despesa = new Despesa();

        // Definindo o credor da despesa
        despesa.setCredor(credor);

        // Definindo a data de vencimento da despesa
        despesa.setDataVencimento(dataVencimento);

        // Definindo o valor da despesa
        despesa.setValor(valor);

        // Definindo a descrição da despesa (opcional)
        despesa.setDescricao(descricao);

        // Definindo o status da despesa como "Pendente"
        despesa.setStatus("Pendente");

        // Definindo a data de pagamento da despesa como null (não pago)
        despesa.setDataPagamento(null);

        // Salvando a despesa no repositório e retornando a despesa salva
        return despesaRepository.save(despesa);
    }

    // Método para atualizar uma despesa
    public Despesa atualizarDespesa(Long id, String credor, LocalDate dataVencimento, BigDecimal valor, String descricao) {
       // Buscando a despesa pelo id
        Despesa despesa = despesaRepository.findById(id).orElseThrow(() -> new RuntimeException("Despesa não encontrada"));

        // Verificando se a despesa está com status "Pago"
        if ("Pago".equals(despesa.getStatus())) {
            // Caso esteja, lançar uma exceção
            throw new RuntimeException("Uma despesa com status 'Pago' não pode ser alterada.");
        }

        // Atualizando os dados da despesa
        despesa.setCredor(credor);

        despesa.setDataVencimento(dataVencimento);

        despesa.setValor(valor);

        despesa.setDescricao(descricao);

        // Salvando a despesa no repositório e retornando a despesa salva
        return despesaRepository.save(despesa);
    }

    // Metodo para consultar todas as despesas
    public List<Despesa> buscarTodasDespesas() {
        // Retornando todas as despesas do repositório
        return despesaRepository.findAll();
    }

    //Metodo para consultar despesas por status
    public List<Despesa> buscarDespesasPorStatus(String status) {
        // Retornando todas as despesas do repositório
        return despesaRepository.findByStatus(status);
    }

    //Metodo para pagar uma despesa
    public Despesa pagarDespesa(Long id) {
        // Buscando a despesa pelo id
        Despesa despesa = despesaRepository.findById(id).orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
        // se a despesa ja esta como paga retorna uma exceçãoe retorna o erro
        if ("Pago".equals(despesa.getStatus())) {
            throw new RuntimeException("Despesa já está paga");
        }


        // Verificando se a despesa está com status "Pago"
        despesa.setDataPagamento(LocalDate.now());
        despesa.setStatus("Pago");

        return despesaRepository.save(despesa);
    }

    //Metodo para estornar uma despesa
    public Despesa estornarDespesa(Long id) {
        Despesa despesa = despesaRepository.findById(id).orElseThrow(() -> new RuntimeException("Despesa não encontrada"));

        despesa.setDataPagamento(null);
        despesa.setStatus("Pendente");

        return despesaRepository.save(despesa);
    }
}


