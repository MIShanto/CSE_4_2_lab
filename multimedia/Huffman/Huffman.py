# Define a node class for the Huffman tree
class Node:
    def __init__(self, char, freq):
        self.char = char
        self.freq = freq
        self.left = None
        self.right = None

# Define a function to build the Huffman tree from a list of nodes
def build_tree(nodes):
    # Repeat until there is only one node left in the list
    while len(nodes) > 1:
        # Sort the nodes by frequency in ascending order
        nodes = sorted(nodes, key=lambda x: x.freq)
        # Pop the two nodes with the lowest frequency
        left = nodes.pop(0)
        right = nodes.pop(0)
        # Create a new node with the sum of their frequencies as the root
        root = Node(None, left.freq + right.freq)
        # Assign the left and right children of the root
        root.left = left
        root.right = right
        # Append the root back to the list
        nodes.append(root)
    # Return the final node as the root of the tree
    return nodes[0]

# Define a function to generate the Huffman codes from the tree
def generate_codes(root, current_code, codes):
    # If the root is a leaf node, store its code in the dictionary
    if root.char is not None:
        codes[root.char] = current_code
    else:
        # Recursively generate codes for the left and right children
        generate_codes(root.left, current_code + "0", codes)
        generate_codes(root.right, current_code + "1", codes)

# Define a function to encode a string using the Huffman codes
def encode(string, codes):
    # Initialize an empty encoded string
    encoded = ""
    # For each character in the string, append its code to the encoded string
    for char in string:
        encoded += codes[char]
    # Return the encoded string
    return encoded

# Define a function to decode an encoded string using the Huffman tree
def decode(encoded, root):
    # Initialize an empty decoded string
    decoded = ""
    # Initialize a current node as the root of the tree
    current_node = root
    # For each bit in the encoded string, traverse the tree accordingly
    for bit in encoded:
        if bit == "0":
            current_node = current_node.left
        else:
            current_node = current_node.right
        # If the current node is a leaf node, append its character to the decoded string and reset the current node to the root
        if current_node.char is not None:
            decoded += current_node.char
            current_node = root
    # Return the decoded string
    return decoded

# Example usage

# Input string
string = "This is a test string"

# Create a list of nodes with each character and its frequency in the string
nodes = []
for char in set(string):
    nodes.append(Node(char, string.count(char)))

# Build the Huffman tree from the nodes
root = build_tree(nodes)

# Generate the Huffman codes from the tree
codes = {}
generate_codes(root, "", codes)

# Print the codes for each character
print("Huffman codes:")
for char in codes:
    print(f"{char}: {codes[char]}")

# Encode the string using the codes
encoded = encode(string, codes)
print(f"Encoded string: {encoded}")

# Decode the encoded string using the tree
decoded = decode(encoded, root)
print(f"Decoded string: {decoded}")