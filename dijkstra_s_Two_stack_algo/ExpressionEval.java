/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra_s_Two_stack_algo;

import java.util.Scanner;

/**
 *
 * @author manishjoshi394
 */
public class ExpressionEval {
    
    public static void main(String[] args)
    {
        arraystack.ArrayStack <Float> num = new arraystack.ArrayStack<>();
        arraystack.ArrayStack <String> opr = new arraystack.ArrayStack<>();
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        scan = new Scanner(s);
        
        while (scan.hasNext())
        {
            s = scan.next();
            switch (s)
            {
                case "+":
                    opr.push(s);
                    break;
                case "-":
                    opr.push(s);
                    break;
                case "*":
                    opr.push(s);
                    break;
                case "/":
                    opr.push(s);
                    break;
                case "(":
                    break;
                case ")":
                    float b = num.pop();
                    float a = num.pop();
                    String sa = opr.pop();
                    float c = 0;
                    switch (sa)
                    {
                        case "+":
                            c = a + b;
                            break;
                        case "-":
                            c = a - b;
                            break;
                        case "*":
                            c = a * b;
                            break;
                        case "/":
                            c = a / b;
                            break;
                    }
                    num.push(c);
                    break;
                default:
                    num.push(Float.parseFloat(s));
            }
            
        }
        System.out.print(num.pop());
        
    }
}
