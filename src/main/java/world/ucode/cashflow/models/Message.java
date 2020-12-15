package world.ucode.cashflow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The type Message.
 */
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

    /**
     * Instantiates a new Message.
     *
     * @param text the text
     * @param tag  the tag
     */
    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }
}
