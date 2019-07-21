package com.abhishek.app.resource;

import com.abhishek.app.model.Quote;
import com.abhishek.app.model.Quotes;
import com.abhishek.app.repository.QuotesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DBServiceResource {
	
	@Autowired
    private QuotesRepository quotesRepository;

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username)
    {
        return getQuotesByUserName(username);
    }
    
    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes)
    {
    	System.out.println("quotes "+quotes+" User Name "+quotes.getUserName());
    	
    	quotes.getQuotes().stream().map(quote->new Quote(quotes.getUserName(),quote))
    	.forEach(quote->quotesRepository.save(quote));
    	return getQuotesByUserName(quotes.getUserName());
    }
    
    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String userName)
    {
    	List<Quote> quotes=quotesRepository.findByUserName(userName);
    	quotesRepository.delete(quotes);
    	return getQuotesByUserName(userName);
    }

	private List<String> getQuotesByUserName(String userName) {
		
		return quotesRepository.findByUserName(userName).stream().map(Quote::getQuote).collect(Collectors.toList());
	}
}
