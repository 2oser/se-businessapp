package com.businessapp.logic;


import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * ********************************************************************************
 * Local implementation class (unfinished) of calculator logic.
 * <p>
 * Instance is invoked with public interface method nextToken( Token tok ) passing
 * an input token that is created at the UI from a key event. Input tokens are defined
 * in CalculatorIntf and comprised of digits [0-9,.], numeric operators [+,-,*,/,VAT]
 * and control tokens [\backspace,=,C,CE,K_1000].
 * <p>
 * Outputs are are passed through display properties:<p>
 * 	- CalculatorIntf.DISPLAY for numbers and<p>
 * 	- CalculatorIntf.SIDEAREA for VAT calculations.
 * <p>
 * Method(s):
 *	- public void nextToken( Token tok );	;process next token from UI controller
 *
 */
class CalculatorLogic implements CalculatorLogicIntf {

	private StringBuffer dsb = new StringBuffer();
	private String operation ="";
	private String[] numbers;
	private String[] operations;
	private String[] equations;


	/**
	 * Local constructor.
	 */
	CalculatorLogic() {
		nextToken( Token.K_C );		// reset buffers
	}

	/**
	 * Process next token from UI controller. Tokens are defined in CalculatorIntf.java
	 * <p>
	 * Outputs are are passed through display properties:
	 * 	- CalculatorIntf.DISPLAY for numbers and
	 * 	- CalculatorIntf.SIDEAREA for VAT calculations.
	 * <p>
	 * @param tok the next Token passed from the UI, CalculatorViewController.
	 */
	public void nextToken( Token tok ) {
		String d = tok==Token.K_DOT? "." : CalculatorLogicIntf.KeyLabels[ tok.ordinal() ];
		try {
			switch( tok ) {
				case K_0: case K_1: case K_2: case K_3: case K_4:
				case K_5: case K_6: case K_7: case K_8: case K_9:
					appendBuffer( d );
					break;

				case K_1000:
					nextToken( Token.K_0 );
					nextToken( Token.K_0 );
					nextToken( Token.K_0 );
					break;

				case K_DIV:
					if(dsb.toString().trim().endsWith("0")) {
						throw new ArithmeticException("ERR: div by zero");
					}
					appendBuffer( d );
					break;
				case K_MUL:
					appendBuffer( d );
					break;
				case K_PLUS:
					appendBuffer( d );
					break;

				case K_MIN:
					appendBuffer( d );
					break;
				case K_EQ:
					String eq = dsb.toString();
					String op = eq;

					if(eq.endsWith(".")){
						CalculatorLogicIntf.SIDEAREA.set("Fehlerhafte Eingabe: " + dsb.toString());
						break;
					}


					String [] newEq = searchDotOp(eq);
					String fullEq = Arrays.toString(newEq);

					fullEq = fullEq.replace(",", "").replace("[", "").replace("]", "").replace(" ", "").trim();
					System.out.println("neue Rechnung: " + Arrays.toString(newEq));
					System.out.println("Rechenoperation : " + fullEq);


					numbers = fullEq.trim().split("[-+/*]");
					System.out.println(Arrays.toString(numbers));

					operations = fullEq.trim().split("[0-9.]+");
					System.out.println(Arrays.toString(operations));
					//if(operations[0].equals("")) System.out.print("Index 0: " + operations[0]);

					//System.out.println(Arrays.toString(searchDotOp(eq, numbers, operations);
					Double side = calculate(eq, numbers, operations);
					CalculatorLogicIntf.SIDEAREA.set(dsb.toString() + " = " + side);
					break;


               /*
                if(eq.contains("[+]")) {
                    String[] array = eq.split("[-+/*]");
                    System.out.println("ArrayInhalt 0: " + array[0]);
                    System.out.println("ArrayInhalt 1: " + array[1]);

                    System.out.println("operation: " + operation);

                    double output = Double.parseDouble(array[0]) + Double.parseDouble(array[1]);
                    CalculatorLogicIntf.SIDEAREA.set(output + "\n");
                }

                if(eq.contains("[-]")) {
                    String[] array = eq.split("[-]");
                    System.out.println("ArrayInhalt 0: " + array[0]);
                    System.out.println("ArrayInhalt 1: " + array[1]);

                    System.out.println("operation: " + operation);

                    double output = Double.parseDouble(array[0]) - Double.parseDouble(array[1]);
                    CalculatorLogicIntf.SIDEAREA.set(output + "\n");
                }
                */


				case K_VAT:
					double brutto = Double.parseDouble(CalculatorLogicIntf.DISPLAY.get());
					double mwst = Math.round((brutto/119*9)*100.0)/100.0;
					double netto = Math.round((brutto - mwst)*100.0)/100.0;
					CalculatorLogicIntf.SIDEAREA.set(
							"Brutto: " + brutto + "\n" +
									VAT_RATE + "% MwSt: " + mwst + "\n" +
									"Netto: " + netto +  "\n"
					);
					break;

				case K_DOT:
					appendBuffer( d );
					break;

				case K_BACK:
					dsb.setLength( Math.max( 0, dsb.length() - 1 ) );
					break;

				case K_C:
					CalculatorLogicIntf.SIDEAREA.set( "" );
				case K_CE:
					dsb.delete( 0,  dsb.length() );
					break;

				default:
			}
			String display = dsb.length()==0? "0" : dsb.toString();
			CalculatorLogicIntf.DISPLAY.set( display );

		} catch( ArithmeticException e ) {
			CalculatorLogicIntf.DISPLAY.set( e.getMessage() );
		}
	}


	/*
	 * Private method(s).
	 */
	private void appendBuffer( String d ) {
		if( dsb.length() <= DISPLAY_MAXDIGITS ) {
			dsb.append( d );
		}
	}

	private double RoundTo2Decimals(double val) {
		DecimalFormat df2 = new DecimalFormat("###.##");
		return Double.valueOf(df2.format(val));
	}

	private double calculate(String eq, String[] numbers, String[] operations){
		double output = Double.parseDouble(numbers[0]);
		int y = 1;
		for(int x=1; x < numbers.length; x++){
			String operand = operations[y];
			switch(operand) {
				case "+": output = output + Double.parseDouble(numbers[x]); y++; break;
				case "-": output = output - Double.parseDouble(numbers[x]); y++; break;
				//case "*": output = output * Double.parseDouble(numbers[x]); y++; break;
				//case "/": output = output / Double.parseDouble(numbers[x]); y++; break;
			}
		}
		return output;
	}


	private String [] searchDotOp(String eq) {
		double output = 0;
		int n=0;

		equations = eq.trim().split("");
		System.out.println(Arrays.toString(equations));

		String [] newArray = new String [equations.length];

		for(int i=0; i<equations.length; i++){
			String temp = equations[n];
			System.out.println(temp);

			if(temp.equals("*")){
				output = Double.parseDouble(equations[i-1]) * Double.parseDouble(equations[i+1]);
				newArray[i-1] = ("" + output).trim();
				n=+3;
			}
			if(temp.equals("/")){
				output = Double.parseDouble(equations[i-1]) / Double.parseDouble(equations[i+1]);
				newArray[i-1] = ("" + output).trim();
				n=+3;
			}

			if(temp.equals("")) break;

/*
				if(equations[n+1].equals("*") || equations[n+1].equals("/")){
						n++;
				}

*/
			else {
				newArray[i] = equations[n];
				n++;
			}
		}
		return newArray;
	}


}
