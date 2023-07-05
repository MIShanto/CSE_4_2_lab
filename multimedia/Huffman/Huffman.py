class Node:
    def __init__(self, char, freq):
        self.char = char
        self.freq = freq
        self.left = self.right = None

def build_tree(nodes):
    while len(nodes) > 1:
        nodes.sort(key=lambda x: x.freq)
        left, right = nodes[:2]
        root = Node(None, left.freq + right.freq)
        root.left, root.right = left, right
        nodes = nodes[2:] + [root]
    return nodes[0]

def generate_codes(root, current_code, codes):
    if root.char:
        codes[root.char] = current_code
    else:
        generate_codes(root.left, current_code + "0", codes)
        generate_codes(root.right, current_code + "1", codes)

def encode(string, codes):
    return ''.join(codes[char] for char in string)

def decode(encoded, root):
    decoded = ""
    current_node = root
    for bit in encoded:
        current_node = current_node.left if bit == "0" else current_node.right
        if current_node.char:
            decoded += current_node.char
            current_node = root
    return decoded

string = "This is a test \n\n\n\n 4545 ; = $ \n\n\n string"
nodes = [Node(char, string.count(char)) for char in set(string)]
root = build_tree(nodes)
codes = {}
generate_codes(root, "", codes)
print("Huffman codes:")
for char in codes:
    print(f"{char}: {codes[char]}")
encoded = encode(string, codes)
print(f"Encoded string: {encoded}")
decoded = decode(encoded, root)
print(f"Decoded string: {decoded}")
