# JHuffman

JHuffman is a Java-based application that implements the Huffman coding algorithm, a classic lossless data compression technique based on character frequencies. The project allows users to compress text files to reduce their storage footprint, as well as decompress previously encoded files back to their original state.

This software was developed as a project for the course *Algortimi e Strutture Dati* during the BSc *Computer Science and Electronic Engineering* at the *University of Perugia*.

## Prerequisites
To run or compile this project, ensure you have the following installed on your system:

* Java Development Kit (JDK) 8 or higher.
* A Java-compatible IDE or access to the Java CLI.

## Code Structure and Workflow

The software operates through the following logical phases:

1. Frequency Analysis: Scans the input text file to count the occurrences of each unique character.
2. Huffman Tree Construction: Populates leaf nodes into a priority queue. Nodes are iteratively extracted in pairs and merged to build a single rooted binary tree.
3. Code Generation: Assigns a unique binary path (`0` for left branches, `1` for right branches) to each character based on its position in the tree.
4. Encoding & Decoding: Replaces original characters with their respective bit sequences during compression, and reverses the process during decompression.

## Performance & Test Results

The table below demonstrates the compression efficiency and performance metrics of the **JHuffman** algorithm across various text sizes using a Lorem Ipsum generator. 

| Number of Characters | Original Size | Compressed Size | Compression Ratio | Encode Time |
| :---: | :---: | :---: | :---: | :---: |
| 10 | 1KB | 1KB | 57% | 1 ms |
| 50 | 1KB | 1KB | 51% | 3 ms |
| 100 | 2KB | 1KB | 48% | 7 ms |
| 500 | 4KB | 2KB | 47% | 46 ms |
| 1.000 | 7KB | 4KB | 46% | 54 ms |
| 5.000 | 32KB | 13KB | 46% | 316 ms |
| 10.000 | 62KB | 39KB | 46% | 475 ms |
| 50.000 | 302KB | 139KB | 46% | 2147 ms |
| 100.000 | 603KB | 278KB | 46% | 4623 ms |
| 500.000 | 3.007KB | 1384KB | 46% | 66478 ms |
| 1.000.000 | 6.018KB | 2769KB | 46% | 348058 ms |

> **Note:** The Compression Ratio is calculated using the formula:  
> `((Bit input - Bit encoded input) / Bit input) * 100`

The Compression Ration reaches a limit at 46% problably due to the predictability in the generation of the Lorem Ipsum.
## License

This project is licensed under the MIT License. See the LICENSE file inside the repository for more details.
