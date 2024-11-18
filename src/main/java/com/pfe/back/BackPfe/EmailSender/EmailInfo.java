package  com.pfe.back.BackPfe.EmailSender;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class emailinfo implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idmail;
	private String destinataire;
	private String body;
	private String topic;
	private String typedestinataire;
	
	public Long getIdmail() {
		return idmail;
	}
	public void setIdmail(Long idmail) {
		this.idmail = idmail;
	}
	public String getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public emailinfo(Long idmail, String destinataire, String body, String topic, String typedestinataire) {
		this.idmail = idmail;
		this.destinataire = destinataire;
		this.body = body;
		this.topic = topic;
		this.typedestinataire = typedestinataire;
	}
	public emailinfo() {
		super();
	}

	
	
	
	
	
	
	
	
}
