# JHuffman
A project for the course 

# JHuffman

JHuffman is a Java-based application that implements the Huffman coding algorithm, a classic lossless data compression technique based on character frequencies. The project allows users to compress text files to reduce their storage footprint, as well as decompress previously encoded files back to their original state.

This project was developer for the *Algortimi e Strutture Dati* during the BSc *Computer Science and Electronic Engineering* at the *University of Perugia*

## Prerequisites
To run or compile this project, ensure you have the following installed on your system:

* Java Development Kit (JDK) 8 or higher
* A Java-compatible IDE (such as IntelliJ IDEA or Eclipse) or access to the Java CLI

## Code Structure and Workflow

The software operates through the following logical phases:

1. Frequency Analysis: Scans the input text file to count the occurrences of each unique character.
2. Huffman Tree Construction: Populates leaf nodes into a priority queue. Nodes are iteratively extracted in pairs and merged to build a single rooted binary tree.
3. Code Generation: Assigns a unique binary path (`0` for left branches, `1` for right branches) to each character based on its position in the tree.
4. Encoding & Decoding: Replaces original characters with their respective bit sequences during compression, and reverses the process during decompression.

## Performance & Test Results

The table below demonstrates the compression efficiency and performance metrics of the **JHuffman** algorithm across various text sizes. 

| Input File | Number of Characters | Original Size | Output File | Compressed Size | Compression Ratio | Execution Time | Decompression Status |
| :--- | :---: | :---: | :--- | :---: | :---: | :---: | :---: |
| 10.txt | 10 | 1KB | 10_output_short.txt | 0KB | 57.0% | 1 ms | Passed |

> **Note:** The Compression Ratio is calculated using the formula:  
> `((Bit input - Bit encoded input) / Bit input) * 100`

## License

This project is licensed under the MIT License. See the LICENSE file inside the repository for more details.
