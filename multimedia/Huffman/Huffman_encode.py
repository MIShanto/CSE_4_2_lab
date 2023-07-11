import json

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

# Read input string from text file
with open('input.txt', 'r') as f:
    string = f.read()

nodes = [Node(char, string.count(char)) for char in set(string)]
root = build_tree(nodes)
codes = {}
generate_codes(root, "", codes)

# Save encoded string to text file
encoded = encode(string, codes)
with open('encoded.txt', 'w') as f:
    f.write(encoded)

# Save codes to text file as a JSON object
with open('codes.json', 'w') as f:
    json.dump(codes, f)

# Print results
print("Huffman codes:")
for char in codes:
    print(f"{char}: {codes[char]}")
print(f"Encoded string: {encoded}")
