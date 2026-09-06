package br.edu.psicopedagogico.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Representa um aluno acompanhado pelo setor psicopedagogico.
 * O campo anotacoesClinicas so deve ser exposto/gravado quando o
 * usuario logado tiver perfil ADMIN_PSICO (regra aplicada nas
 * camadas de Servlet/JSP, nao no modelo).
 */
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id; // UUID
    private String nome;
    private LocalDate dataNascimento;
    private String ciclo;  // INFANTIL, FUNDAMENTAL_1, FUNDAMENTAL_2
    private String status; // ATIVO, INATIVO
    private String anotacoesClinicas;

    public Aluno() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnotacoesClinicas() {
        return anotacoesClinicas;
    }

    public void setAnotacoesClinicas(String anotacoesClinicas) {
        this.anotacoesClinicas = anotacoesClinicas;
    }
}
