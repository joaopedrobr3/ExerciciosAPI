package org.serratec.Aula03.Controller;

import java.util.List;
import java.util.Optional;

import org.serratec.Aula03.Domain.Pedido;
import org.serratec.Aula03.Repository.PedidoRepository;
import org.serratec.Aula03.exeption.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
     
	// Injeção de dependência do repositório de pedidos
	@Autowired
     private PedidoRepository pedidoRepository;
     
	//  Método para listar todos os pedidos
     @GetMapping
     public List<Pedido> listar(){
          return pedidoRepository.findAll();
     }
     
	//  Método para buscar pedido por ID, 
	// lançando exceção personalizada se não encontrado
     @GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado com id: " + id));
		return ResponseEntity.ok(pedido);
		
	}
    // Método para inserir um novo pedido,
	// validando os dados de entrada
     @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido inserir(@Valid @RequestBody Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
    
	// Método para atualizar um pedido existente,
	// verificando se o pedido existe antes de atualizar
     @PutMapping("/{id}")
	public ResponseEntity<Pedido> atualizar(@Valid @RequestBody Pedido pedido,
			@PathVariable Long id) {
		// Verificar se o pedido existe antes de tentar atualizar,
		// retornando 404 Not Found se não existir
		if(!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		// Garantir que o ID do pedido a ser atualizado seja o mesmo do caminho da URL para evitar inconsistências
		pedido.setId(id);
		pedido = pedidoRepository.save(pedido);
		return ResponseEntity.ok(pedido);
	}
    // Método para remover um pedido, verificando se o pedido existe antes de tentar remover
     @DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		// Remover o pedido do banco de dados usando o ID fornecido e retornar uma resposta sem conteúdo (204 No Content)
		pedidoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
    // Método para buscar pedido por descrição, utilizando stream para filtrar a lista de pedidos e retornando o primeiro resultado encontrado
	@GetMapping("/buscar")
	public ResponseEntity<Pedido> buscarPorNome(@RequestParam String descricao) {
		// Utilizar o método findAll() do repositório para obter a lista de pedidos, em seguida, 
		// usar stream para filtrar os pedidos pela descrição fornecida e retornar o primeiro resultado encontrado
		Optional<Pedido> pedido = pedidoRepository.findAll()
		        .stream()
				.filter(c -> c.getDescricao().equalsIgnoreCase(descricao))
				.findFirst();
		// Utilizar o método findAll() do repositório para obter a lista de pedidos, em seguida, 
		// usar stream para filtrar os pedidos pela descrição fornecida e retornar o primeiro resultado encontrado
		if(pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}
		return ResponseEntity.notFound().build();
	}


     
}

