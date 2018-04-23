import java.util.*;
import java.lang.*;

public class BrobInt {

   public static final BrobInt ZERO = new BrobInt(  "0" );
   public static final BrobInt ONE  = new BrobInt(  "1" );
   public static final BrobInt TWO  = new BrobInt(  "2" );
   public static final BrobInt THREE = new BrobInt(  "3" );
   public static final BrobInt FOUR = new BrobInt(  "4" );
   public static final BrobInt FIVE = new BrobInt(  "5" );
   public static final BrobInt SIX = new BrobInt(  "6" );
   public static final BrobInt SEVEN = new BrobInt(  "7" );
   public static final BrobInt EIGHT = new BrobInt(  "8" );
   public static final BrobInt NINE = new BrobInt(  "9" );
   public static final BrobInt TEN = new BrobInt( "10" );

   private int sign = 0;
   private int[] brobArray = null;
   private String reversed = "";
   private String internalValue = "";

   public BrobInt() {
     this.sign = 0;
     this.internalValue = "0";
   }

   public BrobInt(String value) {

      if (value == "" || value == null) {
         throw new IllegalArgumentException();
      }

      for (int i = 0; i < value.length(); i++) {
         validateDigits(value.charAt(i));
      }

      if (value.charAt(0) == '-') {
         sign = 1;

         this.brobArray = new int[value.length()-1];

         for (int i = 1; i < value.length(); i++) {
          this.internalValue += value.charAt(i);
          this.reversed += value.charAt( value.length() - i);
          this.brobArray[i-1] = value.charAt(i) - '0';
         }

      } else if (value.charAt(0) == '+') {
         sign = 0;

         this.brobArray = new int[value.length()-1];

         for (int i = 1; i < value.length(); i++) {
           this.internalValue += value.charAt(i);
           this.reversed += value.charAt( value.length() - i);
           this.brobArray[i-1] = value.charAt(i) - '0';
         }

      } else {
         sign = 0;

         this.brobArray = new int[value.length()];

         this.internalValue = value;

         for (int i = 0; i < value.length(); i++){
           this.reversed += value.charAt( (value.length() - 1) - i);
           this.brobArray[i] = value.charAt(i) - '0';
         }

      }
   }

   public int getSign(){
      return this.sign;
   }

   public void setSign(int value){
      this.sign = value;
   }

   public String getInternalValue(){
      return this.internalValue;
   }

   public void setInternalValue(int value){
      this.sign = value;
   }

   public int[] getBrobArray(){
      return this.brobArray;
   }

   public void setBrobArray(int[] value){
      this.brobArray = value;
   }

   public boolean validateDigits(Character value) {
      for (int i = 0; i < "0123456789+-".length(); i++){
         if (value == "0123456789+-".charAt(i)){
            return true;
         }
      }
      throw new IllegalArgumentException();
   }

   public BrobInt reverser() {
      return new BrobInt(new StringBuilder(this.internalValue).reverse().toString());
   }

   public static BrobInt reverser( BrobInt bint ) {
      return bint.reverser();
   }

   public BrobInt add( BrobInt bint ) {
      int[] a = null;
      int[] b = null;

      int s = 0;
      int d = 0;
      int r = 0;

      String result = "";

      int[] v1 = bint.getBrobArray();

      if (sign == 1 && bint.getSign() == 1){
         s = 1;
      } else if (sign == 0 && bint.getSign() == 1){
         return subtract(new BrobInt(bint.getInternalValue()));
      } else if (sign == 1 && bint.getSign() == 0){
         return bint.subtract(new BrobInt(internalValue));
      }

      if (this.brobArray.length >= v1.length) {
         a = this.brobArray;
         b = v1;
      } else {
         a = v1;
         b = this.brobArray;
      }

      int bIndex = b.length - 1;

      for (int i = a.length - 1; i >= 0; i--){

         if (a.length == b.length){
            d = (a[i] + b[i] + r);
         } else {
            if (bIndex >= 0){
               d = (a[i] + b[bIndex] + r);
               bIndex = bIndex - 1;
            } else {
               d = a[i] + r;
            }
         }

         if (d > 9) {
            r = 1;
            d = d - 10;
         } else {
            r = 0;
         }

         result += d;
      }

      if (r == 1){
         result += 1;
      }

      if ( s == 1 ){
         result += '-';
      }

      BrobInt output = new BrobInt(new BrobInt(result).reverser().toString());
      return new BrobInt(output.toString());
   }

   public BrobInt subtract(BrobInt bint) {
      String input = bint.getInternalValue();

      String v1 = "";
      String v2 = "";

      int d = 0;
      int b1 = 0;

      int s = 0;
      int ws = 0;

      String result = "";

      if (this.sign == 0 && bint.getSign() == 1) {
         return add(new BrobInt(bint.getInternalValue()));
      }

      if (this.sign == 1 && bint.getSign() == 0) {
        result += '-';

        BrobInt temp = new BrobInt(this.internalValue);

        result += temp.add(bint).toString();

        return new BrobInt(result);
      }

      if (sign == 1 && bint.getSign() == 1) {
         s = 1;
         ws = 1;
      }

      if (this.internalValue.length() >= input.length()){
         v1 = internalValue;
         v2 = input;
      } else {
         v1 = input;
         v2 = internalValue;
         s = 1;
      }

      if (this.internalValue.length() == input.length()){

         if (this.compareTo(new BrobInt(input)) < 0) {

            if (ws == 1) {
               return new BrobInt(input).subtract(new BrobInt(internalValue));
            }

            if (this.internalValue.length() == 1 && this.internalValue.charAt(0) == '0') {
              result += ('-' + input);
              return new BrobInt(result.toString());
            }

            s = 1;
         }

      }

      int index2 = v2.length() - 1;

      for (int i = v1.length() - 1; i >= 0; i--) {

         if (v1.length() == v2.length()) {

            if (((v1.charAt(i) - '0') - b1) >= (v2.charAt(i) - '0')){

               d = ((v1.charAt(i) - '0') - b1) - (v2.charAt(i) - '0');
               b1 = 0;

            } else {

               d = ((v1.charAt(i) - '0') + 10) - (v2.charAt(i) - '0');
               b1 = 1;

            }

         } else {

            if (index2 >= 0) {

               if (((v1.charAt(i) - '0') - b1) >= (v2.charAt(index2) - '0')) {
                  d = ((v1.charAt(i) - '0') - b1) - (v2.charAt(index2) - '0');
                  b1 = 0;
               } else {
                  d = ((v1.charAt(i) - '0') + 10 - b1) - (v2.charAt(index2) - '0');
                  b1 = 1;
               }

            } else {

               d = (v1.charAt(i) - '0') - b1;
               b1 = 0;

            }
         }

         index2 = index2 - 1;
         result += d;
      }
      if (s == 1){
        result += '-';
      }

      BrobInt output = new BrobInt(new BrobInt(result).reverser().toString());
      return new BrobInt(output.toString());
   }

   public BrobInt multiply(BrobInt bint) {
      BrobInt v1;
      BrobInt v2;

      BrobInt output = new BrobInt( "0" );

      if (this.internalValue.length() >= bint.getInternalValue().length()){
         v1 = new BrobInt(this.internalValue);
         v2 = new BrobInt(bint.getInternalValue());
      } else {
         v1 = new BrobInt(bint.getInternalValue());
         v2 = new BrobInt(this.internalValue);
      }

      int arrayCount = (v2.getInternalValue().length() / 9) + 1;
      int[] array = new int[arrayCount];

      if (v2.getInternalValue().length() < 10){
         array[0] = Integer.parseInt(v2.getInternalValue());
      } else {
         String temp = "";

         for (int i = 0; i < arrayCount; i++){

            if (i + 9 < v2.getInternalValue().length()){
               temp = v2.getInternalValue().substring(i * 9, i * 9 + 9);
               array[i] = Integer.parseInt(temp);
            } else {
               temp = v2.getInternalValue().substring(i * 9,  i * 9 + (v2.getInternalValue().length() - i));
               array[i] = Integer.parseInt(temp);
            }

         }
      }

      for (int i = 0; i < arrayCount; i++) {

         for (int j = 0; j < array[i]; j++) {
            output = output.add(new BrobInt(v1.getInternalValue()));
         }

      }

      if ((sign == 1 && bint.getSign() == 0) || (sign == 0 && bint.getSign() == 1)) {
         output.setSign(1);
      } else {
         output.setSign(0);
      }

      return output;
   }

   public BrobInt divide( BrobInt bint ) {

     int c = 0;
     int h = 0;

     BrobInt temp = null;

     if ( this.sign != bint.sign ) {

        if ( this.sign == 1 ) {
           h = this.sign;
           this.sign = bint.sign;
        } else {
           h = bint.sign;
           bint.sign = this.sign;
        }

     }

     if (this.equals(bint)) {
        return new BrobInt("1");
     } else if (0 > this.compareTo(bint)) {
        return new BrobInt("0");
     }

     temp = new BrobInt(bint.toString());

     while (0 <= this.compareTo(temp)) {
        temp.internalValue = new BrobInt(temp.toString()).add(bint).toString();
        c++;
        temp.update();
     }

     BrobInt answer = new BrobInt(String.valueOf(c));

     if ( this.sign == bint.sign ) {
        answer.sign = 1;
     } else {
        answer.sign = 0;
     }

     return answer;
   }

   public BrobInt remainder( BrobInt bint ) {
     BrobInt temp = null;

     this.sign = bint.sign;

    if (this.equals(bint)) {
      return new BrobInt("0");
    } else if (0 > this.compareTo(bint)) {
      return new BrobInt("0");
    }

    temp = new BrobInt( bint.toString() );

    while (0 <= this.compareTo(temp)) {
      temp.internalValue = (new BrobInt(temp.toString()).add(bint)).toString();
      temp.update();
    }

    temp.internalValue = (new BrobInt(temp.toString()).subtract(bint)).toString();
    temp.update();

    return new BrobInt(this.subtract(temp).toString());
   }

   public void update() {
      int i = 0;

      int length = 0;
      int maxChars = 8;

      int start = this.internalValue.length() - 8;
      int end = this.internalValue.length();

      length = (int) Math.floor(((this.internalValue.length() - 1 ) / maxChars) + 1);

      this.brobArray = new int[length];

      while ( end >= maxChars ) {
         this.brobArray[i] = Integer.parseInt(this.internalValue.substring(start, end));
         start -= maxChars;
         end -= maxChars;
         i++;
      }

      if (end > 0) {
         this.brobArray[i] = Integer.parseInt(internalValue.substring(0, end));
      }
   }


   public int compareTo(BrobInt bint) {

     // handle the signs here
      if( 1 == this.sign && 0 == bint.getSign() ) {
         return -1;
      } else if( 0 == this.sign && 1 == bint.getSign() ) {
         return 1;
      }

     // the signs are the same at this point
     // check the length and return the appropriate value
     //   1 means this is longer than bint, hence larger
     //  -1 means bint is longer than this, hence larger
      if(this.internalValue.length() > bint.getInternalValue().length() ) {
         return 1;
      } else if(this.internalValue.length() < bint.getInternalValue().length() ) {
         return (-1);

     // otherwise, they are the same length, so compare absolute values
      } else {
         for( int i = 0; i < this.internalValue.length(); i++ ) {
            Character a = Character.valueOf( internalValue.charAt(i) );
            Character b = Character.valueOf( bint.getInternalValue().charAt(i) );
            if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
               return 1;
            } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
               return (-1);
            }
         }
      }
      return 0;
   }

   public boolean equals( BrobInt bint ) {
      return this.internalValue.equals(bint.toString());
   }

   public static BrobInt valueOf(long value) throws NumberFormatException {
      BrobInt bint = null;
      try {
         bint = new BrobInt(new Long(value).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println(nfe);
      }
      return bint;
   }

   public String toString() {
      return internalValue;
   }

   public void toArray(int[] value) {
      System.out.println(Arrays.toString(value));
   }

   public void toArray(byte[] value) {
      System.out.println(Arrays.toString(value));
   }


   public static void main( String[] args ) {
      System.exit( 0 );
   }
}
