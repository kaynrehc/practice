package com.enthusys.play;

/**
 * User: mchernyak
 * Date: 1/11/14
 * Time: 12:50 PM
 */
public  class Tree
{
    static int i[];// = new int[9];
    public static void main(String... args)
    {
        int eye[] = new int[0];
        try
        {
            try{
                System.out.println(i.length);
            }
            finally{
                i = eye;
            }
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            System.err.println("**** " + i.length);
        }
    }
}
