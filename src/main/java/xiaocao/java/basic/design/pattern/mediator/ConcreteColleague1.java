package xiaocao.java.basic.design.pattern.mediator;

public class ConcreteColleague1 extends Colleague {

	public ConcreteColleague1(Mediator mediator) {
		super(mediator);
	}
	
	public void send(String message) {
		this.mediator.send(message, this);
	}
	
	public void notifyMessage(String message){   
        System.out.println("同事1得到消息："+ message);   
    }

}
