package Adapter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BookModel.BookTitle;

public class BookTitleCard extends JPanel {
	private BookTitle bookTitle;
	public BookTitleCard(BookTitle bookTitle) {
		if (bookTitle == null) return;
		this.bookTitle = bookTitle;
		setLayout(null);
		
		JButton button = new JButton("Xem bản sao");
		
		String id = bookTitle.getTitleID();
		String title = bookTitle.getTitle();
		String authorName = bookTitle.getAuthors();
		String publisher = bookTitle.getPublisher();
		int quantity = bookTitle.getNumOfItem();

		JLabel idLabel = new JLabel("ID: " + id);
		JLabel titleLabel = new JLabel(title);
		JLabel authorLabel = new JLabel("Tác giả: " + authorName);
		JLabel publisherLable = new JLabel("Nhà xuất bản: " + publisher);
		JLabel quantityLable = new JLabel("Tổng bản sao: " + quantity);
		JLabel category = new JLabel("Thể loại: " + bookTitle.getCategory());

		titleLabel.setFont(new Font("Inter", Font.BOLD, 15));
		titleLabel.setBounds(20, 7, titleLabel.getPreferredSize().width, titleLabel.getPreferredSize().height);

		idLabel.setFont(new Font("Inter", Font.PLAIN, 10));
		idLabel.setBounds(20, 30, idLabel.getPreferredSize().width, idLabel.getPreferredSize().height);

		authorLabel.setBounds(400, 10, 150, authorLabel.getPreferredSize().height);
		authorLabel.setFont(new Font("Inter", Font.PLAIN, 12));

		publisherLable.setBounds(400, 30, 150, publisherLable.getPreferredSize().height);
		publisherLable.setFont(new Font("Inter", Font.PLAIN, 12));

		quantityLable.setBounds(700, 30, 200, quantityLable.getPreferredSize().height);
		quantityLable.setFont(new Font("Inter", Font.BOLD, 12));
		
		category.setBounds(200, 30, 200, category.getPreferredSize().height);
		category.setFont(new Font("Inter", Font.BOLD, 12));
		
		button.setBounds(850, 18, 100, 25);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.getHSBColor(0.56f, 1, 1));
		
//		add(button);
		add(idLabel);
		add(titleLabel);
		add(authorLabel);
		add(publisherLable);
		add(quantityLable);
		add(category);

		Dimension dimension = new Dimension(1000, 55);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));	
	}
	
	public String getIdTitleOnCard() {
		return bookTitle.getTitleID();
	}
	
	
	
	public BookTitle getBookTitle() {
		return bookTitle;
	}
}
