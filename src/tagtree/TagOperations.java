/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tagtree;

import java.util.Stack;

/**
 *
 * @author user
 */

/**
 * For operations on html tags 
 */
public class TagOperations
{  
 /**
 * Recognizes the corresponding tag as opening/closing and acts accordingly 
 * @param htmlData  HTML data string input
 * @param i         Position from which evaluation should start
 * @param tagList   Stack of tags
 * @return          Confirmation of tag open/closed
 */
    public static String recognizeTags(String htmlData, int i, Stack<HtmlTagData> tagList)
    {
        if(htmlData.charAt(i) == '<' && htmlData.charAt(i+1) == '/')
        {
            //System.out.println("Tag closed");
            String tag = identifyTag(htmlData, i+1);
            addCloseTag(htmlData, i+1, tagList,tag);
            return ("Tag closed");
        }
        else if (htmlData.charAt(i) == '<')
        {
            //System.out.println("Tag opened");
            String tag = identifyTag(htmlData, i);
            addOpenTag (htmlData, i, tagList,tag);
            return ("Tag opened");
        }
        else
        {
            return null;
        }
    }
    /**
     * Recognizes string of text
     * @param htmlData  HTML data string input
     * @param i         Position from which evaluation should start
     * @param tagList   Stack of tags
     * @return 
     */
    public static int recognizeSubstring(String htmlData, int i, Stack<HtmlTagData> tagList)
    {
        String tempSubstring = htmlData.substring(i);
        int newTagPos = tempSubstring.indexOf("<");
        tempSubstring = tempSubstring.substring(0,newTagPos);
        HtmlTagData parent = tagList.pop();
        HtmlTagData newData = new HtmlTagData(tempSubstring, parent);
        parent.children.add(newData);
        System.out.println("Substring Added for " + parent.htmlTag);
        tagList.push(parent);
        return newTagPos-1;
    }
    /**
     * Closes a tag, pops it from the tag stack
     * @param htmlData  Html data string
     * @param i         Offset for evaluation to commence
     * @param tagList   Stack of tags
     * @param tag       Tag being closed
     * @return          true if operation successful
     */
    public static Boolean addCloseTag(String htmlData, int i, Stack<HtmlTagData> tagList,String tag)
    {   
        HtmlTagData currentTag = tagList.peek();
        //System.out.println(tag + " " + currentTag.htmlTag);
        if(tag == null ? currentTag.htmlTag == null : tag.equals(currentTag.htmlTag))
        {
            //System.out.println(currentTag.htmlTag+ " Popped");
            tagList.pop();
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Adds a new tag to the tag stack
     * @param htmlData  HTML data string
     * @param i         Offset for evaluation to commence
     * @param tagList   List of tags
     * @param tag       Tag being opened
     * @return          
     */
    public static Boolean addOpenTag (String htmlData, int i, Stack<HtmlTagData> tagList,String tag)
    {
        if(tagList.size() != 0)
        {
            HtmlTagData parent = tagList.pop();
            HtmlTagData newChild = new HtmlTagData(tag,parent);
            parent.children.add(newChild);
            System.out.println("Child added for " + parent.htmlTag);
            tagList.push(parent);
            tagList.push(newChild);
        }
        else
        {
            HtmlTagData newChild = new HtmlTagData(tag,null);
            tagList.push(newChild);
        }
        
        return true;
    }
    
    /**
     * Identifies the tag within < ... > 
     * @param htmlData  HTML string input
     * @param i         Offset for evaluation to commence
     * @return          Tag within < > 
     */
    public static String identifyTag(String htmlData, int i)
    {
        String tempString = htmlData.substring(i);
        int indexOfClose = tempString.indexOf(">");
        if(indexOfClose != -1)
        {
            return tempString.substring(1,indexOfClose); 
        }
        return null;
    }
    
    public static void printTree(HtmlTagData tagRoot)
    {
        
    }
}
