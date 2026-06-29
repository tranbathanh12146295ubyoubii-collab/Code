package Adapter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import BookModel.BookTitle;

public class BookTitleAdapter implements Adapter {
	BookTitle bookTitle;

	public BookTitleAdapter(BookTitle bookTitle) {
		this.bookTitle = bookTitle;
	}

	@Override
	public JPanel createPanel() {
		return new BookTitleCard(bookTitle);
	}
}
