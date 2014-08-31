/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tagtree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author George Jacob
 */
class HtmlTagData {
    String htmlTag;
    HtmlTagData parent = null;
    List<HtmlTagData> children = new ArrayList<>();
    
    public HtmlTagData(String htmlTag, HtmlTagData parent) {
        this.htmlTag = htmlTag;
        this.parent = parent;
    }
    
    public void addChild(HtmlTagData child)
    {
        this.children.add(child);
    }
}

public class TagTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        String htmlInput = input.nextLine();
        HtmlTagData root = new HtmlTagData("root",null);
        Stack<HtmlTagData> tagList = new Stack();
        tagList.push(root);
        for(int i=0; i < htmlInput.length(); i++)
        {
            if(htmlInput.charAt(i) == '<')
            {
                String tag = TagOperations.recognizeTags(htmlInput,i,tagList);
                String tempString = htmlInput.substring(i);
                int skip = tempString.indexOf(">");
                //Skipping the tag text
                i += skip;
            }
            else
            {
                //Skipping the rest of the text, till next tag
                i+= TagOperations.recognizeSubstring(htmlInput,i,tagList);
            }
        }
        System.out.println(root.children.get(0).children.size());
        //System.out.println(tagList.size());
        if(tagList.size() > 1)
        {
            System.out.println("Error");
        }
        else
        {
            System.out.println("Build Successfull");
        }
    }    
}
