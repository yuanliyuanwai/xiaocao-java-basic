package xiaocao.java.basic.design.pattern.proxy.source;
import java.lang.reflect.Method;
public class TankTimeProxy implements xiaocao.java.basic.design.pattern.proxy.source.Moveable {
	xiaocao.java.basic.design.pattern.proxy.source.InvocationHandler handler;
  public TankTimeProxy(InvocationHandler handler) {
     super();
 this.handler = handler;
  }
@Override
public void move(){
try{
 Method md = xiaocao.java.basic.design.pattern.proxy.source.Moveable.class.getMethod("move");
 handler.invoike(this,md);
}catch(Exception e){
e.printStackTrace();
}
}}