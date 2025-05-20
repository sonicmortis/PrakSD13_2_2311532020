package Pekan5;

public class HapusSLL {
	// fungsi untuk menghapus head
	public static NodeSLL deleteHead(NodeSLL head) {
		if (head == null)
			return null;
		head = head.next;
		return head;
	}

	// fungsi menghapus node terakhir SLL
	public static NodeSLL removeLastNode(NodeSLL head) {
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return null;
		}
		NodeSLL secondLast = head;
		while (secondLast.next.next != null) {
			secondLast = secondLast.next;
		}
		secondLast.next = null;
		return head;
	}

	// fungsi menghapus node di posisi tertentu
	public static NodeSLL deleteNode(NodeSLL head, int position) {
		NodeSLL temp = head;
		NodeSLL prev = null;

		if (temp == null)
			return head;

		if (position == 1) {
			head = temp.next;
			return head;
		}

		for (int i = 1; temp != null && i < position; i++) {
			prev = temp;
			temp = temp.next;
		}

		if (temp != null) {
			prev.next = temp.next;
		} else {
			System.out.println("Data tidak ada");
		}

		return head;
	}

	// fungsi mencetak SLL
	public static void printList(NodeSLL head) {
		NodeSLL curr = head;
		while (curr != null) {
			System.out.print(curr.data);
			if (curr.next != null)
				System.out.print("-->");
			curr = curr.next;
		}
		System.out.println();
	}
	
	// kelas main
	public static void main(String[] args) {
		// buat SSL 1->2->3->3->4->5->6->null
		NodeSLL head = new NodeSLL(1);
		head.next = new NodeSLL(2);
		head.next.next = new NodeSLL(3);
		head.next.next.next = new NodeSLL(4);
		head.next.next.next.next = new NodeSLL(5);
		head.next.next.next.next.next = new NodeSLL(6);
		
		// cetak list awal
		System.out.println("list awal: ");
		printList(head);
		
		// hapus head
		head = deleteHead(head);
		System.out.println("List setelah head dihapus: ");
		printList(head);
		
		// hapus node terakhir
		head = removeLastNode(head);
		System.out.println("List setelah simpul terakhir dihapus: ");
		printList(head);
		
		// Deleting node at position 2
		int  position = 2;
		head = deleteNode(head, position);
		
		// print list after deletion
		System.out.println("List setelah posisi 2 dihapus: ");
		printList(head);
		
	}
}
