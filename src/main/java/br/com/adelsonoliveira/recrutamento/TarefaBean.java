package br.com.adelsonoliveira.recrutamento;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.adelsonoliveira.recrutamento.dao.DaoGeneric;
import br.com.adelsonoliveira.recrutamento.enums.Situacao;
import br.com.adelsonoliveira.recrutamento.model.Tarefa;

@ManagedBean(name = "tarefaBean")
public class TarefaBean {

	private Tarefa tarefa = new Tarefa();
	private DaoGeneric<Tarefa> daoGeneric = new DaoGeneric<Tarefa>();
	private List<Tarefa> tarefas = new ArrayList<Tarefa>();
	
	@PostConstruct
	public void carregarTarefas() {
		tarefas = daoGeneric.consultar(Tarefa.class);
	}
	
	public String salvar() {
		daoGeneric.salvar(tarefa);
		tarefa = new Tarefa();
		return "index?faces-redirect=true";
	}
	
	public String concluirTarefa(Long id) {
		for(Tarefa tarefa: tarefas){
			if(tarefa.getId().equals(id)) {
				tarefa.setConcluida(Situacao.CONCLUIDA);
				daoGeneric.atualizar(tarefa);
			}
		}
		this.carregarTarefas();
		return "";
	}
	
	public String remover(Long id) {
		daoGeneric.remover(tarefa, id);
		this.carregarTarefas();
		return "";
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
}
