/* 
 * Copyright 2018 Manish Joshi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
