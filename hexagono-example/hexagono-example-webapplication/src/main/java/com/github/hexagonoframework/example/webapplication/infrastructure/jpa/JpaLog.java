package com.github.hexagonoframework.example.webapplication.infrastructure.jpa;

import java.io.Serializable;
import java.lang.String;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class JpaLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    private String id;

    @Column
    private LocalDateTime when;
    
    @Column
    private String message;

    public JpaLog() {
        super();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getWhen() {
        return this.when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

}
