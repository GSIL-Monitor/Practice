package Thinking_in_Java.holding;

import java.util.Stack;

/**
 * Created by DreamYao on 2016/7/1.
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<String>stack=new Stack<String>();
        for (String s:"My dog has fleas".split(" ")){
            stack.push(s);
        }
        while (!stack.empty()){
            System.out.print(stack.pop()+" ");
        }
    }
}