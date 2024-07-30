

import java.io.*;
import java.util.*;
import java.util.function.Function;

public class TestJava {

    private Optional<FileReader> getFileReaderOptional(File file){
        try (FileReader fileReader = new FileReader(file)){
            return Optional.of(fileReader);
        } catch (IOException _) {
            return Optional.empty();
        }
    }
    
    public Long countLines(List<String> filenames) throws IOException {
        return filenames
                .stream()
                .map(File::new)
                .map(this::getFileReaderOptional)
                .flatMap(opt->opt.map(fileReader -> 
                        new BufferedReader(fileReader)
                                .lines().count()).stream())
                .reduce(Long::sum)
                .orElse(0L);
    }


    //no map
    public List<Integer> multiplyEachByTwo(List<Integer> intList){
        List<Integer> result = new ArrayList<>();   	//выделение памяти под результат
        for (int i = 0; i < intList.size(); i++) {  	//итерация по коллекции
            var resultValue = intList.get(i)*2;		//выполнение операции
            result.add(resultValue);              	//добавление элемента к результату
        }
        return result;
    }

    public List<Integer> applyOperationToEach(
            List<Integer> intList,
            Function<Integer, Integer> operation){
        List<Integer> result = new ArrayList<>();   	        //выделение памяти под результат
        for (int i = 0; i < intList.size(); i++) {  	        //итерация по коллекции
            var resultValue = operation.apply(intList.get(i));	//выполнение операции
            result.add(resultValue);              	            //добавление элемента к результату
        }
        return result;
    }

    public <A, B> List<B> listMap(
            List<A> list,
            Function<A, B> f){
        return list             //переменная под результат не требуется
                .stream()       //для использования stream api
                .map(f)         //собственно map
                .toList();      //функция должна возвращать List
    }

    public <T> void printAll(List<T> list){
        list.forEach(System.out::println);
    }

    public static void mapDemo(){
        var list = List.of(1,2,3,4,5,6,7,8,9,0); //есть список
        Function<Integer, Integer> incrementInt = x -> x+1;
        System.out.println(
                list.stream().map(incrementInt).toList()
        );
    }

    public static void flattenDemo(){
        var multidimensionalList = List.of(
                List.of(1,2,3,4),
                List.of(9,7,5),
                List.of(97,89,41,17,13,24)
        );
        Function<Integer, Integer> f = x->x+1;
        var flattenList = multidimensionalList
                .stream()
                .flatMap(list->
                        list
                                .stream()
                                .map(f)
                );
        System.out.println(flattenList.toList());
    }
    public static void flatmapDemo(){
        var multidimensionalList = List.of(
                List.of(1,2,3,4),
                List.of(9,7,5),
                List.of(97,89,41,17,13,24)
        );
    }
    public static void reduceDemo(){
        var list = List.of(1,2,3,4);
        System.out.println( list.stream().reduce((x,y)-> x+y).get());
        System.out.println( list.stream().reduce((x,y)-> x*y).get());
    }

    public static void main(String[] args) {
        mapDemo();
        flattenDemo();
        reduceDemo();
    }

}