package com.mrraxa01.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mrraxa01.todolist.model.Task;
import com.mrraxa01.todolist.service.TaskService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class TaskController {

	@Autowired
	TaskService taskService;

	@ApiOperation(value = "Criando uma nova tarefa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Tarefas Listadas com Sucesso!"),
			@ApiResponse(code = 500, message = "Houve um erro ao listar as tarefas!")

	})

	@PostMapping("/tasks")
	@ResponseStatus(HttpStatus.CREATED)
	public Task createTask(@RequestBody Task task) {
		log.info("Criando uma nova tarefa com as informações [{}]", task);
		return taskService.createTask(task);
	}

	@ApiOperation(value = "Listando todas as tarefas")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Tarefa Criado com Sucesso!"),
			@ApiResponse(code = 500, message = "Houve um erro ao criar a tarefa, verifique as informações!")

	})

	@GetMapping("/tasks")
	@ResponseStatus(HttpStatus.OK)
	public List<Task> getAllTasks() {
		log.info("Listando todas as tarefas cadastradas [{}]");
		return taskService.listAllTasks();
	}

	@ApiOperation(value = "Buscando tarefa por Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Tarefa Encontrada com Sucesso!"),
			@ApiResponse(code = 404, message = "Não foi encontrada nenhuma tarefa com esse Id!")

	})

	@GetMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long id) {
		log.info("Buscando a tarefa com o Id [{}]", id);
		return taskService.findTaskById(id);
	}

	@ApiOperation(value = "Atualizando uma tarefa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Tarefa Atualizada com Sucesso!"),
			@ApiResponse(code = 404, message = "Não foi possível atualizar - tarefa não encontrada")

	})
	@PutMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Task> updateTaskById(@PathVariable(value = "id") Long id, @RequestBody Task task) {
		log.info("Atualizando a tarefa com Id [{}], as novas informações são : [{}]", id, task);
		return taskService.updateTaskById(task, id);
	}

	@ApiOperation(value = "Excluido uma tarefa")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Tarefa Excluida com Sucesso!"),
			@ApiResponse(code = 404, message = "Não foi possível excluir - tarefa não encontrada!")

	})
	
	@DeleteMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> deleteTaskById(@PathVariable(value = "id") Long id) {
		log.info("Deletando a tarefa com Id [{}]", id);

		return taskService.deleteById(id);
	}

}
