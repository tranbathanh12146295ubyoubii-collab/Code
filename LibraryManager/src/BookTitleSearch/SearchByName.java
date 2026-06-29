package BookTitleSearch;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import BookModel.BookTitle;

public class SearchByName implements BookTitleSearch {
	private List<BookTitle> bookTitles;

	public SearchByName(List<BookTitle> bookTitles) {
		this.bookTitles = bookTitles;
	}

	private String removeAccent(String str) { // XÓA DẤU TIẾNG VIỆT
		String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
		return temp.replaceAll("\\p{M}", "");
	}

	@Override
	public List<BookTitle> search(String keyword) {
		List<BookTitle> list = new ArrayList<>();
		if (keyword == null || keyword.isEmpty())
			return list;
		String kw = removeAccent(keyword.toLowerCase().trim());
		for (BookTitle bookTitle : bookTitles) {
			String name = removeAccent(bookTitle.getTitle().toLowerCase());
			if (name.contains(kw)) {
				list.add(bookTitle);
			}
		}
		return list;

	}

}
