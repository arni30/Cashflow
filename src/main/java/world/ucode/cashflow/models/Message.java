package world.ucode.cashflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
    @Getter
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Getter @Setter
    private String text;
    @Setter @Getter
    private String tag;

    public Message() {
    }

    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getTag() {
//        return tag;
//    }
//
//    public void setTag(String tag) {
//        this.tag = tag;
//    }
}
