package cn.ksmcbrigade.RL;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class Libs {

    public static final String strings = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,0,1,2,3,4,5,6,7,8,9,`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,[,],\\,{,},|,;,',:,<,>,/,?,dh,.";
    public static final String colors = "§1,§2,§3,§4,§5,§6,§7,§8,§9,§0,§a,§b,§c,§d,§e,§f,§g,§h,§i,§j,§m,§n,§p,§q,§s,§t,§u,§k,§l,§o,§r";
    public static final Item[] items = (Item[]) get("item");
    public static final Block[] blocks = (Block[]) get("block");
    public static final Enchantment[] enchantments = (Enchantment[]) get("enchant");
    public static final EntityType[] entityTypes = (EntityType[]) get("entity");

    public Libs(){
    }

    public static Object[] get(String type){
        if(type.equalsIgnoreCase("item")){
            ArrayList<Item> itemArrayList = new ArrayList<>();
            Class<?> itemsClass = Items.class;
            for(Field field:itemsClass.getFields()){
                if(Modifier.isStatic(field.getModifiers())){
                    try {
                        itemArrayList.add((Item) field.get(null));
                    } catch (IllegalAccessException e) {
                        System.out.println("Failed in get item:"+field.getName()+"\n"+getExceptionText(e));
                        return null;
                    }
                }
            }
            return itemArrayList.toArray(new Item[itemArrayList.size()]);
        }
        else if(type.equalsIgnoreCase("block")){
            ArrayList<Block> itemArrayList = new ArrayList<>();
            Class<?> itemsClass = Blocks.class;
            for(Field field:itemsClass.getFields()){
                if(Modifier.isStatic(field.getModifiers())){
                    try {
                        itemArrayList.add((Block) field.get(null));
                    } catch (IllegalAccessException e) {
                        System.out.println("Failed in get block:"+field.getName()+"\n"+getExceptionText(e));
                        return null;
                    }
                }
            }
            return itemArrayList.toArray(new Block[itemArrayList.size()]);
        }
        else if(type.equalsIgnoreCase("enchant")){
            ArrayList<Enchantment> itemArrayList = new ArrayList<>();
            Class<?> itemsClass = Enchantments.class;
            for(Field field:itemsClass.getFields()){
                if(Modifier.isStatic(field.getModifiers())){
                    try {
                        itemArrayList.add((Enchantment) field.get(null));
                    } catch (IllegalAccessException e) {
                        System.out.println("Failed in get enchantment:"+field.getName()+"\n"+getExceptionText(e));
                        return null;
                    }
                }
            }
            return itemArrayList.toArray(new Enchantment[itemArrayList.size()]);
        }
        else if(type.equalsIgnoreCase("entity")){
            ArrayList<EntityType> itemArrayList = new ArrayList<>();
            Class<?> itemsClass = EntityType.class;
            for(Field field:itemsClass.getFields()){
                if(Modifier.isStatic(field.getModifiers()) && field.getType().equals(EntityType.class)){
                    try {
                        itemArrayList.add((EntityType) field.get(null));
                    } catch (IllegalAccessException e) {
                        System.out.println("Failed in get entity:"+field.getName()+"\n"+getExceptionText(e));
                        return null;
                    }
                }
            }
            return itemArrayList.toArray(new EntityType[itemArrayList.size()]);
        }
        else{
            return null;
        }
    }

    public static long getRandomNumber(long max){
        return Math.round(Math.random()*max);
    }

    public static String getRandomString(int length){
        String[] strs = strings.split(",");
        String randomStrs = "";
        for(int i=0;i<length;i++){
            String randomStr = strs[(int) Math.floor(Math.random()*(strs.length-1))].replace("dh",",");
            if(randomStrs.equalsIgnoreCase("")){
                randomStrs = randomStr;
            }
            else{
                randomStrs = randomStrs + randomStr;
            }
        }
        return randomStrs;
    }

    public static Item getRandomItem(){
        if (items != null) {
            int r = (int) Math.floor(Math.random()* (items.length-1));
            return items[r];
        }
        else{
            return null;
        }
    }

    public static Block getRandomBlock(){
        if (blocks != null) {
            return blocks[(int) Math.floor(Math.random()* (blocks.length-1))];
        }
        else{
            return null;
        }
    }

    public static Enchantment getRandomEnchant(){
        if (enchantments != null) {
            return enchantments[(int) Math.floor(Math.random()* (enchantments.length-1))];
        }
        else{
            return null;
        }
    }

    public static EntityType getRandomEntity(){
        if (entityTypes != null) {
            return entityTypes[(int) Math.floor(Math.random()* (entityTypes.length-1))];
        }
        else{
            return null;
        }
    }

    public static String getRandomColor(){
        return colors.split(",")[(int) Math.floor(Math.random()*(colors.split(",").length-1))];
    }

    public static Object getRandom(String type,int length){
        if(type.equalsIgnoreCase("number")){
            return getRandomNumber(length);
        }
        else if(type.equalsIgnoreCase("string")){
            return getRandomString(length);
        }
        else if(type.equalsIgnoreCase("color")){
            return getRandomColor();
        }
        else if(type.equalsIgnoreCase("item")){
            return getRandomItem();
        }
        else if(type.equalsIgnoreCase("block")){
            return getRandomBlock();
        }
        else if(type.equalsIgnoreCase("enchant")){
            return getRandomEnchant();
        }
        else if(type.equalsIgnoreCase("entity")){
            return getRandomEntity();
        }
        else{
            return "null";
        }
    }

    public static String aboutRandom(String type){
        if(type.equalsIgnoreCase("number")){
            return getMethodName(Libs.class,"getRandomNumber");
        }
        else if(type.equalsIgnoreCase("string")){
            return getMethodName(Libs.class,"getRandomString");
        }
        else if(type.equalsIgnoreCase("color")){
            return getMethodName(Libs.class,"getRandomColor");
        }
        else if(type.equalsIgnoreCase("item")){
            return getMethodName(Libs.class,"getRandomItem");
        }
        else if(type.equalsIgnoreCase("block")){
            return getMethodName(Libs.class,"getRandomBlock");
        }
        else if(type.equalsIgnoreCase("enchant")){
            return getMethodName(Libs.class,"getRandomEnchant");
        }
        else if(type.equalsIgnoreCase("entity")){
            return getMethodName(Libs.class,"getRandomEntity");
        }
        else{
            return "null";
        }
    }

    public static String getMethodName(Class<?> clazz, String functionName) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(functionName)) {
                return method.toString();
            }
        }
        return "null";
    }

    public static String InvokeLib(String f, String... args){
        try{
            Method method = Libs.class.getMethod(f,new Class[args.length]);
            method.setAccessible(true);
            Object instance = Libs.class.getDeclaredConstructor().newInstance();
            return (String)method.invoke(instance,(Object[]) args);
        }
        catch (Exception e){
            String argss = "";
            for(String arg:args){
                if(argss.equals("")){
                    argss = arg;
                }
                else{
                    argss = argss + "," + arg;
                }
            }
            System.out.println("Failed to invoke "+ f + " args: " + argss);
            return "Failed to invoke "+ f +" args: "+ argss + "\n" + getExceptionText(e);
        }
    }

    public static String getExceptionText(Exception e){
        String text = e.toString();
        for(StackTraceElement ste:e.getStackTrace()){
            text=text+"\nat "+ste;
        }
        return text;
    }
}
