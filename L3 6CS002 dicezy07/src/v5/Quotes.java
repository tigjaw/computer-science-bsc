package v5;

public class Quotes {

	private static String[] quotes = {
			"Whether you think you can or whether you think you can't, you're right.",
			"Henry Ford",
			"You see things; and you say 'Why?' But I dream things that never were; and I say 'Why not?'",
			"George Bernard Shaw",
			"Within each of us lies the power of our consent to health and sickness, to riches and poverty, to freedom and to slavery. It is we who control these, and not another.",
			"Richard Bach (Illusions)",
			"Never look down on anybody unless you're helping him up.",
			"Jesse Jackson",
			"To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment.",
			"Ralph Waldo Emerson",
			"A bird doesn't sing because it has an answer, it sings because it has a song.",
			"Maya Angelou",
			"There is no use trying, said Alice; one can't believe impossible things. I dare say you haven't had much practice, said the Queen. When I was your age, I always did it for half an hour a day. Why, sometimes I've believed as many as six impossible things before breakfast.",
			"Lewis Carroll",
			"The reasonable man adapts himself to the world; the unreasonable one persists in trying to adapt the world to himself. Therefore all progress depends on the unreasonable man.",
			"George Bernard Shaw",
			"The journey is the reward.",
			"Chinese Proverb",
			"People are like stained-glass windows. They sparkle and shine when the sun is out, but when the darkness sets in, their true beauty is revealed only if there is a light from within.",
			"Elizabeth Kubler Ross"};

	public static void getRandomQuote() {
		int quoteIndex = (int) (Math.random() * (quotes.length / 2));
		String what = Quotes.quotes[quoteIndex * 2];
		String who = Quotes.quotes[1 + quoteIndex * 2];
		System.out.printf("\"%s\" : %s", what, who + "\n");
	}

}