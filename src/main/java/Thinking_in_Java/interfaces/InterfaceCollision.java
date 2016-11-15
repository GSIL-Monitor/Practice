package Thinking_in_Java.interfaces;

/**
 * Created by DreamYao on 2016/5/21.P181组合接口时的名字冲突
 */
public class InterfaceCollision {
}
interface I1{
    void f();
}
interface I2{
    int f(int i);
}
interface I3{
    int f();
}
class C{
    public int f(){
        return 1;
    }
}
class C2 implements I1,I2{
    public void f(){}
    public int f(int i){
        return 1;
    }
}
class C3 extends C implements I2{
    public int f(int i){
        return 1;
    }
}
class C4 extends C implements I3{
    public int f(){
        return 1;
    }
}
//class C5 extends C implements I1{
//    public void f(){}
//}
//interface I4 extends I1,I3{}