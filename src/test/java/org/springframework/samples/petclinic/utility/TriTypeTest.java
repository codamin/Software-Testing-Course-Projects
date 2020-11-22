package org.springframework.samples.petclinic.utility;

import com.github.mryf323.tractatus.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TriTypeTest {

	private static final Logger log = LoggerFactory.getLogger(TriTypeTest.class);

	@Test
	public void sampleTest() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(1,1,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.EQUILATERAL, triClass);
	}


	/**
	 * todo
	 * explain your answer here
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 */
	@ClauseCoverage(
		predicate = "a + b + c",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = true)
		})
	@Test
	public void firstIfCC1() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(0, 0, 0);
		Assertions.assertEquals(triOut, 4);
	}
	@ClauseCoverage(
		predicate = "a + b + c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		})
	@Test
	public void firstIfCC2() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(1, 1, 1);
		Assertions.assertEquals(triOut, 3);
	}

	@ClauseCoverage(
		predicate = "m * (d + e + f)",
		valuations = {
			@Valuation(clause = 'm', valuation = true),
			@Valuation(clause = 'd', valuation = true),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		})
	@Test
	public void secondIfCC1() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(1, 2, 4);
		Assertions.assertEquals(triOut, 4);
	}
	@ClauseCoverage(
		predicate = "m * (d + e + f)",
		valuations = {
			@Valuation(clause = 'm', valuation = true),
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = true),
			@Valuation(clause = 'f', valuation = false)
		})
	@Test
	public void secondIfCC2() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(4, 1, 2);
		Assertions.assertEquals(triOut, 4);
	}
	@ClauseCoverage(
		predicate = "m * (d + e + f)",
		valuations = {
			@Valuation(clause = 'm', valuation = true),
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = true)
		})
	@Test
	public void secondIfCC3() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(2, 4, 1);
		Assertions.assertEquals(triOut, 4);
	}
	@ClauseCoverage(
		predicate = "m * (d + e + f)",
		valuations = {
			@Valuation(clause = 'm', valuation = false),
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		})
	@Test
	public void secondIfCC4() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(1, 1, 1);
		Assertions.assertEquals(triOut, 3);
	}

	//	With awareness of the fact that last if of the function has just one implicant terms, but we tested them also.
	@ClauseCoverage(
		predicate = "j * ~d",
		valuations = {
			@Valuation(clause = 'j', valuation = true),
			@Valuation(clause = 'd', valuation = true)
		})
	@Test
	public void thirdIf2CC1() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(2, 2, 1);
		Assertions.assertEquals(triOut, 2);
	}
	@ClauseCoverage(
		predicate = "j * ~d",
		valuations = {
			@Valuation(clause = 'j', valuation = false),
			@Valuation(clause = 'd', valuation = false)
		})
	@Test
	public void thirdIf2CC2() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(1, 2, 3);
		Assertions.assertEquals(triOut, 4);
	}
	@ClauseCoverage(
		predicate = "k * ~f",
		valuations = {
			@Valuation(clause = 'k', valuation = true),
			@Valuation(clause = 'f', valuation = true)
		})
	@Test
	public void thirdIf3CC1() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(2, 1, 2);
		Assertions.assertEquals(triOut, 2);
	}
	@ClauseCoverage(
		predicate = "k * ~f",
		valuations = {
			@Valuation(clause = 'k', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		})
	@Test
	public void thirdIf3CC2() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(1, 3, 2);
		Assertions.assertEquals(triOut, 4);
	}
	@ClauseCoverage(
		predicate = "l * ~e",
		valuations = {
			@Valuation(clause = 'l', valuation = true),
			@Valuation(clause = 'e', valuation = true)
		})
	@Test
	public void thirdIf4CC1() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(2, 1, 2);
		Assertions.assertEquals(triOut, 2);
	}
	@ClauseCoverage(
		predicate = "l * ~e",
		valuations = {
			@Valuation(clause = 'l', valuation = false),
			@Valuation(clause = 'e', valuation = false)
		})
	@Test
	public void thirdIf4CC2() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(1, 3, 2);
		Assertions.assertEquals(triOut, 4);
	}

	@CACC(
		predicate = "a + b + c",
		majorClause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = false)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = false)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = false)
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		})
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		clause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		})
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		})
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		clause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		})
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		})
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		clause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		})
	@Test
	public void firstIfCACC1f() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(1, 1, 1);
		Assertions.assertEquals(triOut, 3);
	}
	@CACC(
		predicate = "a + b + c",
		majorClause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = true)
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		})
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		clause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		})
	@Test
	public void firstIfCACC1t() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(0, 1, 1);
		Assertions.assertEquals(triOut, 4);
	}
	@CACC(
		predicate = "a + b + c",
		majorClause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false)
		},
		predicateValue = true)
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false)
		})
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		clause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false)
		})
	@Test
	public void firstIfCACC2t() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(1, 0, 1);
		Assertions.assertEquals(triOut, 4);
	}
	@CACC(
		predicate = "a + b + c",
		majorClause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true)
		},
		predicateValue = true)
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true)
		})
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		clause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true)
		})
	@Test
	public void firstIfCACC3t() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(1, 1, 0);
		Assertions.assertEquals(triOut, 4);
	}

	@CACC(
		predicate = "m * (d + e + f)",
		majorClause = 'd',
		valuations = {
			@Valuation(clause = 'm', valuation = true),
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		},
		predicateValue = false)
	@CACC(
		predicate = "m * (d + e + f)",
		majorClause = 'e',
		valuations = {
			@Valuation(clause = 'm', valuation = true),
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		},
		predicateValue = false)
	@CACC(
		predicate = "m * (d + e + f)",
		majorClause = 'f',
		valuations = {
			@Valuation(clause = 'm', valuation = true),
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		},
		predicateValue = false)
	@Test
	public void secondIfCACC1f() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(3, 4, 5);
		Assertions.assertEquals(triOut, 1);
	}
	@CACC(
		predicate = "m * (d + e + f)",
		majorClause = 'd',
		valuations = {
			@Valuation(clause = 'm', valuation = true),
			@Valuation(clause = 'd', valuation = true),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		},
		predicateValue = true)
	@CACC(
		predicate = "m * (d + e + f)",
		majorClause = 'm',
		valuations = {
			@Valuation(clause = 'm', valuation = true),
			@Valuation(clause = 'd', valuation = true),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		},
		predicateValue = true)
	@Test
	public void secondIfCACC1t() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(1, 2, 4);
		Assertions.assertEquals(triOut, 4);
	}
	@CACC(
		predicate = "m * (d + e + f)",
		majorClause = 'e',
		valuations = {
			@Valuation(clause = 'm', valuation = true),
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = true),
			@Valuation(clause = 'f', valuation = false)
		},
		predicateValue = true)
	@Test
	public void secondIfCACC2t() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(4, 1, 2);
		Assertions.assertEquals(triOut, 4);
	}
	@CACC(
		predicate = "m * (d + e + f)",
		majorClause = 'f',
		valuations = {
			@Valuation(clause = 'm', valuation = true),
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = true)
		},
		predicateValue = true)
	@Test
	public void secondIfCACC3t() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(2, 4, 1);
		Assertions.assertEquals(triOut, 4);
	}
	@CACC(
		predicate = "m * (d + e + f)",
		majorClause = 'm',
		valuations = {
			@Valuation(clause = 'm', valuation = false),
			@Valuation(clause = 'd', valuation = true),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		},
		predicateValue = true)
	@Test
	public void secondIfCACC4f() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(1, 1, 2);
		Assertions.assertEquals(triOut, 4);
	}

	//	With awareness of the fact that last if of the function has just one implicant terms, but we tested them also.
	@CACC(
		predicate = "j * ~d",
		majorClause = 'j',
		valuations = {
			@Valuation(clause = 'j', valuation = true),
			@Valuation(clause = 'd', valuation = false)
		},
		predicateValue = true)
	@CACC(
		predicate = "j * ~d",
		majorClause = 'd',
		valuations = {
			@Valuation(clause = 'j', valuation = true),
			@Valuation(clause = 'd', valuation = false)
		},
		predicateValue = true)
	@Test
	public void thirdIfCACC1t() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(2, 2, 1);
		Assertions.assertEquals(triOut, 2);
	}
	@CACC(
		predicate = "j * ~d",
		majorClause = 'j',
		valuations = {
			@Valuation(clause = 'j', valuation = false),
			@Valuation(clause = 'd', valuation = true)
		},
		predicateValue = false)
	@CACC(
		predicate = "j * ~d",
		majorClause = 'd',
		valuations = {
			@Valuation(clause = 'j', valuation = false),
			@Valuation(clause = 'd', valuation = true)
		},
		predicateValue = false)
	@Test
	public void thirdIfCACC1f() {
//		infeasible
	}
	@CACC(
		predicate = "k * ~f",
		majorClause = 'k',
		valuations = {
			@Valuation(clause = 'k', valuation = true),
			@Valuation(clause = 'f', valuation = false)
		},
		predicateValue = true)
	@CACC(
		predicate = "k * ~f",
		majorClause = 'f',
		valuations = {
			@Valuation(clause = 'k', valuation = true),
			@Valuation(clause = 'f', valuation = false)
		},
		predicateValue = true)
	@Test
	public void thirdIfCACC2t() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(2, 1, 2);
		Assertions.assertEquals(triOut, 2);
	}
	@CACC(
		predicate = "k * ~f",
		majorClause = 'k',
		valuations = {
			@Valuation(clause = 'k', valuation = false),
			@Valuation(clause = 'f', valuation = true)
		},
		predicateValue = true)
	@CACC(
		predicate = "k * ~f",
		majorClause = 'f',
		valuations = {
			@Valuation(clause = 'k', valuation = false),
			@Valuation(clause = 'f', valuation = true)
		},
		predicateValue = true)
	@Test
	public void thirdIfCACC2f() {
//		infeasible
	}
	@CACC(
		predicate = "l * ~e",
		majorClause = 'l',
		valuations = {
			@Valuation(clause = 'l', valuation = true),
			@Valuation(clause = 'e', valuation = false)
		},
		predicateValue = true)
	@CACC(
		predicate = "l * ~e",
		majorClause = 'e',
		valuations = {
			@Valuation(clause = 'l', valuation = true),
			@Valuation(clause = 'e', valuation = false)
		},
		predicateValue = true)
	@Test
	public void thirdIfCACC3t() {
		TriType tryType = new TriType();
		int triOut = tryType.recognizeTriangleByCode(1, 2, 2);
		Assertions.assertEquals(triOut, 2);
	}
	@CACC(
		predicate = "l * ~e",
		majorClause = 'l',
		valuations = {
			@Valuation(clause = 'l', valuation = false),
			@Valuation(clause = 'e', valuation = true)
		},
		predicateValue = true)
	@CACC(
		predicate = "l * ~e",
		majorClause = 'e',
		valuations = {
			@Valuation(clause = 'l', valuation = false),
			@Valuation(clause = 'e', valuation = true)
		},
		predicateValue = true)
	@Test
	public void thirdIfCACC3f() {
//		infeasible
	}


	private static boolean questionTwo(boolean a, boolean b, boolean c, boolean d, boolean e) {
		boolean predicate = false;
//		predicate: f
//		f =  ab + cd ---> A Test set to cover UTPs = {TTFF, FFTT}
//		~f = ac, ad, bc, bd ---> UTPs = {TFTF, TFFT, FTTF, FTFT}
//		The test set to cover CUTPNFP(which is only on f) = UTPs of f + NFPs of f:
//											{TTFF, FFTT, FTFF, TFFF, FFFT, FFTF}
//		UTPC needs ---> UTPs of f + UTPs of ~f
//		By defenition(and as we can see above), CUTPNFP covers UTPs of f but
//		it does n
		return predicate;
	}
}
