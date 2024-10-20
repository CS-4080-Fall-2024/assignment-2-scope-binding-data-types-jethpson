
public class RubiksCube 
{

    private final String[][][] cube; // 3D array to represent the cube
    private final int size; // Size of each face

    /**
     * Constructor to initialize the cube with a given size.
     *
     * @param size The size of the cube (e.g., 3 for a 3x3 cube).
     */
    public RubiksCube(int size) {
        this.size = size;
        this.cube = new String[6][size][size];
        initializeCube();
    }

    // Initializes the cube with colors
    private void initializeCube() {
        cube[0] = createFace("W");
        cube[1] = createFace("R");
        cube[2] = createFace("B");
        cube[3] = createFace("G");
        cube[4] = createFace("O");
        cube[5] = createFace("Y");
    }

    // Creates a face filled with a specified color
    private String[][] createFace(String color) {
        String[][] face = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                face[i][j] = color;
            }
        }
        return face;
    }

    // Rotates a face 90 degrees clockwise
    private String[][] rotateFaceClockwise(String[][] face) {
        String[][] newFace = new String[size][size]; 
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newFace[j][size - 1 - i] = face[i][j];
            }
        }
        return newFace;
    }

    /**
     * Rotates a specified layer clockwise.
     *
     * @param layer The layer to rotate (0-indexed).
     */
    public void rotateLayer(int layer) {
        cube[0] = rotateFaceClockwise(cube[0]); // Rotate the front face
    
        // Store the current left, back, right, and front edges
        String[] temp = new String[size];
        for (int i = 0; i < size; i++) {
            temp[i] = cube[2][i][layer]; // Left face's column
        }
    
        // Rotate adjacent sides
        for (int i = 0; i < size; i++) {
            cube[2][i][layer] = cube[1][i][layer]; // Back to left
            cube[1][i][layer] = cube[3][i][layer]; // Right to back
            cube[3][i][layer] = cube[4][i][layer]; // Up to right
            cube[4][i][layer] = temp[i]; // Left to up
        }
    }

    /**
     * Rotates a specified layer counterclockwise.
     *
     * @param layer The layer to rotate (0-indexed).
     */
    public void rotateLayerCounterclockwise(int layer) {
        cube[0] = rotateFaceClockwise(rotateFaceClockwise(rotateFaceClockwise(cube[0]))); // Rotate the front face
    
        // Store the current left, back, right, and front edges
        String[] temp = new String[size];
        for (int i = 0; i < size; i++) {
            temp[i] = cube[2][i][layer]; // Left face's column
        }
    
        // Rotate adjacent sides
        for (int i = 0; i < size; i++) {
            cube[2][i][layer] = cube[4][i][layer]; // Up to left
            cube[4][i][layer] = cube[3][i][layer]; // Right to up
            cube[3][i][layer] = cube[1][i][layer]; // Back to right
            cube[1][i][layer] = temp[i]; // Left to back
        }
    }

    // Prints the current state of the cube to the console
    public void printCube() {
        System.out.println("Front:");
        printFace(cube[0]);
        System.out.println("Back:");
        printFace(cube[1]);
        System.out.println("Left:");
        printFace(cube[2]);
        System.out.println("Right:");
        printFace(cube[3]);
        System.out.println("Up:");
        printFace(cube[4]);
        System.out.println("Down:");
        printFace(cube[5]);
    }

    /**
     * Prints a face of the cube to the console.
     *
     * @param face The face to print.
     */
    private void printFace(String[][] face) {
        for (String[] row : face) {
            for (String tile : row) {
                System.out.print(tile + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    
    public static void main(String[] args) {
        int size = 3; // Size of the cube
        RubiksCube cube = new RubiksCube(size);
        cube.printCube();

        System.out.println("Rotating layer 0 clockwise...");
        cube.rotateLayer(0); 
        cube.printCube();

        System.out.println("Rotating layer 0 counterclockwise...");
        cube.rotateLayerCounterclockwise(0); 
        cube.printCube();
    }
}
