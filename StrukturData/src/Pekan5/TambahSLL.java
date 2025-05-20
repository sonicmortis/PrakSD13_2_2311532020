package Pekan5;

public class TambahSLL {

	public static NodeSLL insertAtFront(NodeSLL head, int value) {
		NodeSLL new_node = new NodeSLL(value);
		new_node.next = head;
		return new_node;
	}

	public static NodeSLL insertAtEnd(NodeSLL head, int value) {
		NodeSLL newNode = new NodeSLL(value);
		if (head == null) {
			return newNode;
		}
		NodeSLL last = head;
		while (last.next != null) {
			last = last.next;
		}
		last.next = newNode;
		return head;
	}

	static NodeSLL GetNode(int data) {
		return new NodeSLL(data);
	}

	static NodeSLL insertPos(NodeSLL headNode, int position, int value) {
		NodeSLL head = headNode;
		if (position < 1) {
			System.out.println("Invalid Position");
			return head;
		}

		// posisi pertama (insert di depan)
		if (position == 1) {
			NodeSLL new_node = new NodeSLL(value);
			new_node.next = head;
			return new_node;
		} else {
			NodeSLL current = head;
			while (position-- > 2) {
				if (current == null) {
					System.out.println("Posisi di luar jangkauan");
					return head;
				}
				current = current.next;
			}
			if (current != null) {
				NodeSLL newNode = new NodeSLL(value);
				newNode.next = current.next;
				current.next = newNode;
			} else {
				System.out.println("Posisi di luar jangkauan");
			}
		}
		return head;
	}

	public static void printList(NodeSLL head) {
		NodeSLL curr = head;
		while (curr != null) {
			System.out.print(curr.data);
			if (curr.next != null) System.out.print("-->");
			curr = curr.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		NodeSLL head = new NodeSLL(2);
		head.next = new NodeSLL(3);
		head.next.next = new NodeSLL(5);
		head.next.next.next = new NodeSLL(6);

		System.out.print("Senarai berantai awal: ");
		printList(head);

		System.out.print("Tambah 1 simpul di depan: ");
		int data = 1;
		head = insertAtFront(head, data);
		printList(head);

		System.out.print("Tambah 1 simpul di belakang: ");
		int data2 = 7;
		head = insertAtEnd(head, data2);
		printList(head);

		System.out.print("Tambah 1 simpul ke posisi 4: ");
		int data3 = 4;
		int pos = 4;
		head = insertPos(head, pos, data3);
		printList(head);
	}
}
