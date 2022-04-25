package futoshikiIDSL;

/**
 * public class usePuzzleIDSL.
 * Futoshiki Solver using Internal-DSL.
 * @author 0622803 - Joshua Woodyatt
 */
public class usePuzzleIDSL {

	public static void main(String[] args) {
		new Futoshiki().puzzle().with_grid_of(4)
		.cell_IDs("a0","b0","c0","d0")
		.cell_IDs("a1","b1","c1","d1")
		.cell_IDs("a2","b2","c2","d2")
		.cell_IDs("a3","b3","c3","d3")
		.row_values(2,0,0,0)
		.row_values(0,3,0,0)
		.row_values(0,0,0,0)
		.row_values(0,0,0,0)
		.cell("a2").is_less_than_cell("a3")
		.cell("c2").is_greater_than_cell("d2")
		.cell("d2").is_greater_than_cell("d3")
		.cell("a3").is_greater_than_cell("b3")
		.distinct_rows()
		.distinct_columns()
		.solve();
	}
	
/*	public static void main(String[] args) {
		new Futoshiki().puzzle().with_grid_of(5)
		.cell_IDs("a0","b0","c0","d0","e0")
		.cell_IDs("a1","b1","c1","d1","e1")
		.cell_IDs("a2","b2","c2","d2","e2")
		.cell_IDs("a3","b3","c3","d3","e3")
		.cell_IDs("a4","b4","c4","d4","e4")
		.row_values(2,0,0,0,0)
		.row_values(0,3,0,0,0)
		.row_values(0,0,0,0,0)
		.row_values(0,0,0,0,0)
		.row_values(0,0,0,0,0)
		.cell("a2").is_less_than_cell("a3")
		.cell("c2").is_greater_than_cell("d2")
		.cell("d2").is_greater_than_cell("d3")
		.cell("a3").is_greater_than_cell("b3")
		.distinct_rows()
		.distinct_columns()
		.solve();
	}*/

}